package com.douyu.controller;

import com.douyu.dao.*;
import com.douyu.pojo.*;
import com.douyu.util.JedisUtil;
import com.douyu.util.LotteryCommon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;

import java.sql.Timestamp;
import java.text.ParseException;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;

/**
 * @description:
 * @author: Dangerous
 * @time: 2020/3/12 11:51
 */
@RestController
@RequestMapping("/lottery")
public class LotteryController {
    @Autowired
    private PrizeMapper prizeMapper;
    @Autowired
    private LotteryManagementMapper lotteryManagementMapper;
    @Autowired
    private WinnerRecordMapper winnerRecordMapper;
    @Autowired
    private LotteryUserMapper lotteryUserMapper;
    @Autowired
    private LotteryLevelMapper lotteryLevelMapper;

    @RequestMapping("/init/{lottetyId}")
    public String init(@PathVariable String lottetyId) {

        Jedis jedis = JedisUtil.getJedis();
        try{
            jedis.flushAll();
            Prize[] arr = initPrize(lottetyId);
            LotteryManagement lotteryManagement=initLotteryManagement(lottetyId);

            HashSet<Prize> set = new HashSet<>();

            //计算总价值和中奖概率(存入)
            LotteryCommon.calculation(arr, lotteryManagement);

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            JedisUtil.returnJedis(jedis);
        }

        return "init ok";
    }
    @RequestMapping("/start/{lottetyId}/{enginerrId}")
    public String start(@PathVariable String lottetyId,@PathVariable String enginerrId) throws ParseException {

//        LotteryUser lotteryUser = lotteryUserMapper.selectByPrimaryKey(enginerrId);
//        if(lotteryUser==null){
//            return "你没有注册,不能抽奖";
//        }
//        Integer userLevel=lotteryUser.getUserLevel();
//        LotteryLevel lotteryLevel = lotteryLevelMapper.selectByPrimaryKey(userLevel);
//
//        int limitTime=lotteryLevel.getLimitTime();


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

    private void saveWinnerRecord(WinnerRecord winnerRecord){
        winnerRecordMapper.insert(winnerRecord);
    }
    private LotteryManagement initLotteryManagement(String lotteryId){
        return lotteryManagementMapper.selectByPrimaryKey(lotteryId);
    }

    private  Prize[] initPrize(String lotteryId) {
        PrizeExample example=new PrizeExample();
        example.createCriteria().andLotteryIdEqualTo(lotteryId);

        List<Prize> prizeList = prizeMapper.selectByExample(example);
        Prize[] prizeArray=new Prize[prizeList.size()];
        return prizeList.toArray(prizeArray);
    }


    /**
     * 抽奖
     * @param lotteryId
     * @return
     */
    public  WinnerRecord luckDraw(String lotteryId,String engineerId) throws ParseException {
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
                String redeemCode=UUID.randomUUID().toString().replaceAll("-","");
                Prize prize = prizeMapper.selectByPrimaryKey(id.intValue());
                WinnerRecord winnerRecord = new WinnerRecord(id.toString(), redeemCode, prize.getPrizeName(),
                        engineerId, engineerId, 1, new Timestamp(System.currentTimeMillis()));
                return winnerRecord;
            }

        } catch (Exception e) {
            throw new RuntimeException("CACHE_PRIZE_LIST_ERROR_MSG");
        } finally {
            JedisUtil.returnJedis(jedis);
        }

        return null;

    }

    /**
     * 保存抽奖记录
     * @param lotteryId
     * @return
     */
    public  void saveLotteryManagement(String lotteryId) {
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
