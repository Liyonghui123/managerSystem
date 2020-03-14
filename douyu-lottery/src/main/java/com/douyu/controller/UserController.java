package com.douyu.controller;

import com.douyu.dao.*;
import com.douyu.pojo.*;
import com.douyu.util.JedisUtil;
import com.douyu.util.LotteryCommon;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/user")
public class UserController {

    private final String EXCEED_LIMIT_TIME="E1";

    private final String REACH_LOTTERY_TIME="E2";

    @Autowired
    private PrizeMapper prizeMapper;
    @Autowired
    private LotteryUserMapper lotteryUserMapper;
    @Autowired
    private LotteryManagementMapper lotteryManagementMapper;
    @Autowired
    private LotteryLevelMapper lotteryLevelMapper;
    @Autowired
    private WinnerRecordMapper winnerRecordMapper;

    /**
     * 开始抽奖
     * @param lottetyId
     * @param enginerrId
     * @return
     * @throws ParseException
     */
    @RequestMapping(value = "/start/{lottetyId}/{enginerrId}",method = RequestMethod.GET)
    public String start(@PathVariable String lottetyId, @PathVariable String enginerrId){

        LotteryUser lotteryUser = lotteryUserMapper.selectByPrimaryKey(enginerrId);
        if(lotteryUser==null){
            return "你没有注册,不能抽奖";
        }
        Integer userLevel=lotteryUser.getUserLevel();
        List<LotteryLevel> lotteryLevels = lotteryLevelMapper.selectByExample(new LotteryLevelExample());

        LotteryManagement lotteryManagement = lotteryManagementMapper.selectByPrimaryKey(lottetyId);
        //抽奖总次数
        Integer lotteryTotailNum = lotteryManagement.getLotteryTotailNum();
        //S成功 E1已超过限制抽奖次数！E2 抽奖已结束,已达到抽奖次数。
        String s = LotteryCommon.verifyNum(lotteryLevels, userLevel, enginerrId, lottetyId, lotteryTotailNum);
        if(s.equals(EXCEED_LIMIT_TIME)){
            return "已超过限制抽奖次数！";
        }else if(s.equals(REACH_LOTTERY_TIME)){
            return "E2 抽奖已结束,已达到抽奖次数";
        }

        //抽奖
        WinnerRecord winnerRecord= luckDraw(lottetyId, enginerrId);
        if(winnerRecord!=null) {
            saveWinnerRecord(winnerRecord);
            saveLotteryManagement(lottetyId);
            return "欧皇,你中了"+winnerRecord.getPrizeId()+"等奖"+",兑换码为"+winnerRecord.getRedeemCode();
        }else{
            return "非酋，快去洗洗手再来";
        }
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

            id = jedis.zscore(LotteryCommon.PRIZE_LIST + lotteryId, index.intValue()+"");

            if (id != null) {
                //设置每天
                if (!jedis.exists(engineerId)) {
                    jedis.incr(engineerId);
                    jedis.expire(engineerId, 60 * 60 * 24);
                } else {
                    jedis.incr(engineerId);
                }

                //每个轮询的次数
                jedis.incr(lotteryId + engineerId);

                //删除缓存奖品
                jedis.zrem(LotteryCommon.PRIZE_LIST + lotteryId, index + "");

                //数据库相关操作
                String redeemCode= UUID.randomUUID().toString().replaceAll("-","");
                Prize prize = prizeMapper.selectByPrimaryKey(id.intValue());
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
        winnerRecordMapper.insert(winnerRecord);
    }

    /**
     * 更新抽奖管理信息
     * @param lotteryId
     * @return
     */
    private void saveLotteryManagement(String lotteryId) {
        Jedis jedis = JedisUtil.getJedis();
        try {
            LotteryManagement lotteryManagement = lotteryManagementMapper.selectByPrimaryKey(lotteryId);
            int lotteryNum = Integer.valueOf(jedis.get(lotteryId));
            lotteryManagement.setLotteryNum(lotteryNum);

            int lotterySurplusNum = lotteryManagement.getLotteryTotailNum() - lotteryNum;
            lotteryManagement.setLotterySurplusNum(lotterySurplusNum);

            if(lotteryManagement.getLotteryTotailNum() <= lotteryNum){
                lotteryManagement.setLotteryOver(1);
            }
            lotteryManagementMapper.updateByPrimaryKey(lotteryManagement);

        } catch (Exception e) {
            throw new RuntimeException("SAVE_LOTTERY_MANAGEMENT_ERROR_MSG");
        } finally {
            JedisUtil.returnJedis(jedis);
        }
    }
}
