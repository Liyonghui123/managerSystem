package com.douyu.controller;

import com.douyu.pojo.*;
import com.douyu.service.LotteryLevelService;
import com.douyu.service.LotteryManagementService;
import com.douyu.service.PrizeService;
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

import java.util.List;

/**
 * @description:
 * @author: Dangerous
 * @time: 2020/3/12 11:51
 */
@RestController
@Api(tags = "抽奖初始化相关接口")
@RequestMapping("/init")
public class LotteryInitController {

    @Autowired
    private LotteryManagementService lotteryManagementService;
    @Autowired
    private LotteryLevelService lotteryLevelService;
    @Autowired
    private PrizeService prizeService;

    /**
     * 初始化抽奖
     * @param lottetyId
     * @return
     */
    @ApiOperation(value = "/start/{lottetyId}", notes = "初始化抽奖")
    @RequestMapping(value = "/start/{lottetyId}",method = RequestMethod.GET)
    public ResponseEntity<String> init(@PathVariable String lottetyId) {

        Jedis jedis = JedisUtil.getJedis();
        try{
            jedis.flushAll();
            Prize[] arr = initPrize(lottetyId);
            LotteryManagement lotteryManagement=initLotteryManagement(lottetyId);

            //计算总价值和中奖概率(存入)
            LotteryCommon.calculation(arr, lotteryManagement);
            List<LotteryLevel> lotteryLevels = lotteryLevelService.selectByExample(new LotteryLevelExample());
            LotteryCommon.ruleList(lotteryLevels, lottetyId);
            return ResponseEntity.status(HttpStatus.CREATED).body("init ok");
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            JedisUtil.returnJedis(jedis);
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }

    /**
     * 初始化活动
     * @param lotteryId
     * @return
     */
    private LotteryManagement initLotteryManagement(String lotteryId){
        return lotteryManagementService.selectByPrimaryKey(lotteryId);
    }

    /**
     * 初始化奖品
     * @param lotteryId
     * @return
     */
    private  Prize[] initPrize(String lotteryId) {
        PrizeExample example=new PrizeExample();
        example.createCriteria().andLotteryIdEqualTo(lotteryId);

        List<Prize> prizeList = prizeService.selectByExample(example);
        Prize[] prizeArray=new Prize[prizeList.size()];
        return prizeList.toArray(prizeArray);
    }







}
