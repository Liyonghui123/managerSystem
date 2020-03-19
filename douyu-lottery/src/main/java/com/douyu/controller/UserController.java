package com.douyu.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.douyu.pojo.*;
import com.douyu.service.*;
import com.douyu.util.JedisUtil;
import com.douyu.util.LotteryCommon;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;

import java.sql.Timestamp;
import java.text.ParseException;
import java.util.List;
import java.util.UUID;

/**
 * @description:
 * @author: Dangerous
 * @time: 2020/3/14 16:43
 */
@RestController
@Api(tags = "用户抽奖相关接口")
@RequestMapping("/user")
public class UserController {
    @Autowired
    private PrizeService prizeService;
    @Autowired
    private LotteryUserService lotteryUserService;
    @Autowired
    private LotteryManagementService lotteryManagementService;
    @Autowired
    private LotteryLevelService lotteryLevelService;
    @Autowired
    private WinnerRecordService winnerRecordService;

    /**
     * 开始抽奖
     * @param lottetyId
     * @param enginerrId
     * @return
     * @throws ParseException
     */
    @ApiOperation(value = "/start/{lottetyId}/{enginerrId}", notes = "抽奖")
    @RequestMapping(value = "/start/{lottetyId}/{enginerrId}",method = RequestMethod.POST)
    @SentinelResource(value = "lottery",defaultFallback = "lotteryError")
    public ResponseEntity start(@PathVariable String lottetyId, @PathVariable String enginerrId){

        LotteryUser lotteryUser = lotteryUserService.selectByPrimaryKey(enginerrId);
        if(lotteryUser==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("你没有注册,不能抽奖");
        }
        Integer userLevel=lotteryUser.getUserLevel();
        List<LotteryLevel> lotteryLevels = lotteryLevelService.selectByExample(new LotteryLevelExample());

        LotteryManagement lotteryManagement = lotteryManagementService.selectByPrimaryKey(lottetyId);
        //抽奖总次数
        Integer lotteryTotailNum = lotteryManagement.getLotteryTotailNum();

        //S成功 E1已超过限制抽奖次数！E2 抽奖已结束,已达到抽奖次数。
        String s = LotteryCommon.verifyNum(lotteryLevels, userLevel, enginerrId, lottetyId, lotteryTotailNum);
        if(s.equals(LotteryCommon.EXCEED_LIMIT_TIME)){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("已超过限制抽奖次数！");
        }else if(s.equals(LotteryCommon.REACH_LOTTERY_TIME)){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("抽奖已结束,已达到抽奖次数");
        }else if (s.equals(LotteryCommon.NO_LOTTERY_AUTHORITY_ASSIGNED)){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("您还没有抽奖权限");
        }
        String result;
        //抽奖
        WinnerRecord winnerRecord= luckDraw(lottetyId, enginerrId);
        if(winnerRecord!=null) {
            saveWinnerRecord(winnerRecord);
            saveLotteryManagement(lottetyId);
            result="欧皇,你中了"+winnerRecord.getPrizeId()+"等奖"+",兑换码为"+winnerRecord.getRedeemCode();
            return ResponseEntity.ok(result);
        }else{
            result="非酋，快去洗洗手再来";
            return ResponseEntity.ok(result);
        }
    }
    public ResponseEntity lotteryError(Throwable e){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("抽奖人数太多，请稍后重试");
    }

    /**
     * 抽奖
     * @param lotteryId
     * @return
     */
    private   WinnerRecord luckDraw(String lotteryId,String engineerId){
        Jedis jedis = JedisUtil.getJedis();
        Long index;
        Double id;
        try {

            index = jedis.incr(lotteryId);
            //每个轮询的次数
            jedis.incr(lotteryId + engineerId);

            id = jedis.zscore(LotteryCommon.PRIZE_LIST + lotteryId, index.intValue()+"");

            if (id != null) {
                //设置每天
                if (!jedis.exists(engineerId)) {
                    jedis.incr(engineerId);
                    jedis.expire(engineerId, 60 * 60 * 24);
                } else {
                    jedis.incr(engineerId);
                }

                //删除缓存奖品
                jedis.zrem(LotteryCommon.PRIZE_LIST + lotteryId, index + "");

                //数据库相关操作
                String redeemCode= UUID.randomUUID().toString().replaceAll("-","");
                Prize prize = prizeService.selectByPrimaryKey(id.intValue());
                return new WinnerRecord(id.toString(), redeemCode, prize.getPrizeName(),
                        engineerId, engineerId, 1, new Timestamp(System.currentTimeMillis()));
            }

        } catch (Exception e) {
            throw new RuntimeException("CACHE_PRIZE_LIST_ERROR_MSG");
        } finally {
            JedisUtil.returnJedis(jedis);
        }
        return null;
    }
    /**
     * 保存中奖记录
     * @param winnerRecord
     */
    private void saveWinnerRecord(WinnerRecord winnerRecord){
        winnerRecordService.insert(winnerRecord);
    }

    /**
     * 更新抽奖管理信息
     * @param lotteryId
     * @return
     */
    private void saveLotteryManagement(String lotteryId) {
        Jedis jedis = JedisUtil.getJedis();
        try {
            LotteryManagement lotteryManagement = lotteryManagementService.selectByPrimaryKey(lotteryId);
            int lotteryNum = Integer.valueOf(jedis.get(lotteryId));
            lotteryManagement.setLotteryNum(lotteryNum);

            int lotterySurplusNum = lotteryManagement.getLotteryTotailNum() - lotteryNum;
            lotteryManagement.setLotterySurplusNum(lotterySurplusNum);

            if(lotteryManagement.getLotteryTotailNum() <= lotteryNum){
                lotteryManagement.setLotteryOver(1);
            }
            lotteryManagementService.updateByPrimaryKey(lotteryManagement);

        } catch (Exception e) {
            throw new RuntimeException("SAVE_LOTTERY_MANAGEMENT_ERROR_MSG");
        } finally {
            JedisUtil.returnJedis(jedis);
        }
    }
}
