package com.douyu.controller;

import com.douyu.dao.LotteryManagementMapper;
import com.douyu.dao.PrizeMapper;
import com.douyu.pojo.LotteryManagement;
import com.douyu.pojo.Prize;
import com.douyu.pojo.PrizeExample;
import com.douyu.util.JedisUtil;
import com.douyu.util.LotteryCommon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;

import java.util.HashSet;
import java.util.List;

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
    public String start(@PathVariable String lottetyId,@PathVariable String enginerrId) {
        //抽奖
        String s = luckDraw(lottetyId, enginerrId);
        //saveLotteryManagement(lottetyId);
        return "你中了"+s+"等奖";
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
    public  String luckDraw(String lotteryId,String engineerId) {
        String prizeId = null;
        Jedis jedis = JedisUtil.getJedis();
        try {

            Long index = jedis.incr(lotteryId);

            Double id = jedis.zscore(LotteryCommon.PRIZE_LIST + lotteryId, index.intValue()+"");
            boolean flag=true;
            while(flag) {
                if (id != null) {
                    flag=false;
                    prizeId = String.valueOf(id.intValue());

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
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("CACHE_PRIZE_LIST_ERROR_MSG");
        } finally {
            JedisUtil.returnJedis(jedis);
        }
        return prizeId;
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
