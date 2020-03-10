package com.douyu.service;

import com.douyu.controller.LotteryController;
import com.douyu.util.JedisUtil;
import com.douyu.util.LotteryUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

import java.util.List;
import java.util.UUID;

/**
 * @description:
 * @author: Dangerous
 * @time: 2020/3/10 15:03
 */
@Component
public class MyRunnable1 implements Runnable{
    @Autowired
    //TODO 多线程防注入
    private JedisUtil jedisUtil;

    private Jedis jedis=new JedisUtil().getJedis();

    @Override
    public void run() {

        try {
            // 查询剩余奖品总数
            String key = getPrize();
            System.err.println("线程" + Thread.currentThread().getName() + "中奖奖品id为：" + key);

        } catch (Exception e) {
            System.err.println("算法计算异常：异常原因 = " + e.getMessage());
        }finally {
            jedisUtil.returnJedis(jedis);
        }
    }

    private String getPrize() {
        //获取中奖奖品ID
        String key = LotteryUtil.lottery(jedis);
        //精确监控单个奖品剩余数
        jedis.watch(key, LotteryController.RESIDUAL_QUANTITY);

        if("-1".equals(key) || "0".equals(jedis.get(key))){
            jedis.unwatch();
            key = getPrize();
        }else{

//			key = AvailablePrize(key);
            //开启redis事物
            Transaction tx = jedis.multi();
            //减少总库存
            tx.incrBy(LotteryController.RESIDUAL_QUANTITY, -1);
            //减少中奖奖品总库存
            tx.incrBy(key, -1);
            //提交事务，如果此时watch key被改动了，则返回null
            List<Object> listObj = tx.exec();
            if (listObj != null) {
                //多个进程同时 key>0 key相等时
                jedis.sadd("failuse", UUID.randomUUID().toString() + key);
                //中奖成功业务逻辑
                System.out.println("用户中奖成功！！！");
            } else {
                //重新计算奖品
                key = getPrize();
            }
        }
        return key;
    }

    //是否是有效奖品
//	private String AvailablePrize(String key) {
//		int prizeNum = Integer.valueOf(jedis.get(key));
//
//		//奖品无效重新计算验证
//		if(prizeNum <= 0){
//			 AvailablePrize(LotteryUtil.lottery(jedis));
//		}
//		return key;
//	}

}
