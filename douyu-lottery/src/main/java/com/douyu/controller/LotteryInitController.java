package com.douyu.controller;

import com.douyu.dao.LotteryLevelMapper;
import com.douyu.dao.LotteryManagementMapper;
import com.douyu.dao.PrizeMapper;
import com.douyu.pojo.*;
import com.douyu.util.JedisUtil;
import com.douyu.util.LotteryCommon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;

import java.util.List;

/**
 * @description:
 * @author: Dangerous
 * @time: 2020/3/12 11:51
 */
@RestController
@RequestMapping("/init")
public class LotteryInitController {

    @Autowired
    private LotteryManagementMapper lotteryManagementMapper;
    @Autowired
    private LotteryLevelMapper lotteryLevelMapper;
    @Autowired
    private PrizeMapper prizeMapper;

    /**
     * 初始化抽奖
     * @param lottetyId
     * @return
     */
    @RequestMapping(value = "/start/{lottetyId}",method = RequestMethod.GET)
    public String init(@PathVariable String lottetyId) {

        Jedis jedis = JedisUtil.getJedis();
        try{
            jedis.flushAll();
            Prize[] arr = initPrize(lottetyId);
            LotteryManagement lotteryManagement=initLotteryManagement(lottetyId);

            //计算总价值和中奖概率(存入)
            LotteryCommon.calculation(arr, lotteryManagement);
            List<LotteryLevel> lotteryLevels = lotteryLevelMapper.selectByExample(new LotteryLevelExample());
            LotteryCommon.ruleList(lotteryLevels, lottetyId);

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            JedisUtil.returnJedis(jedis);
        }

        return "init ok";
    }

    /**
     * 初始化活动
     * @param lotteryId
     * @return
     */
    private LotteryManagement initLotteryManagement(String lotteryId){
        return lotteryManagementMapper.selectByPrimaryKey(lotteryId);
    }

    /**
     * 初始化奖品
     * @param lotteryId
     * @return
     */
    private  Prize[] initPrize(String lotteryId) {
        PrizeExample example=new PrizeExample();
        example.createCriteria().andLotteryIdEqualTo(lotteryId);

        List<Prize> prizeList = prizeMapper.selectByExample(example);
        Prize[] prizeArray=new Prize[prizeList.size()];
        return prizeList.toArray(prizeArray);
    }







}
