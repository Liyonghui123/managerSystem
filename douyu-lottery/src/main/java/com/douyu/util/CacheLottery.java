package com.douyu.util;

import com.douyu.pojo.LotteryManagement;
import com.douyu.pojo.Prize;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;

import java.util.HashSet;

/**
 * @description:
 * @author: Dangerous
 * @time: 2020/3/11 17:09
 */
public class CacheLottery {
        @Autowired
        private static JedisUtil jedisUtil;

        public static Jedis jedis = jedisUtil.getJedis();

        public static final String PRIZE_LIST = "prizeList";

        public static final String PRIZE_ID = "prizeId";

        public static void main(String[] args) {


            HashSet<Integer> set = new HashSet<Integer>();
//		set = randomSet(1, 2258, 2036, set);
            System.err.println(set.size());
            int index=0;
            for (Integer integer : set) {
                System.out.println("索引index="+ index+++"元素"+integer);
            }
        }


        /**
         * 缓存奖品信息
         * @param lotteryId
         * @param sponsorPoint
         * @param arr
         */
        public static void cachePrizeListByLotteryId(String lotteryId,int sponsorPoint ,Prize[] arr) {

            try {
                if(jedis == null){
                    jedis = jedisUtil.getJedis();
                }

                //初始化本活动奖品抽奖次数
                jedis.set(lotteryId, "0");

                //奖品容器
                HashSet<Prize> set = new HashSet<Prize>();

                randomSet(1, sponsorPoint, set, arr);

                Pipeline p = jedis.pipelined();

                //修改清除本次奖品列表
                p.del(PRIZE_LIST + lotteryId);

                for (Prize obj : set) {
                    //缓存奖品随机码和奖品ID
                    //TODO
                    //p.zadd(PRIZE_LIST + lotteryId, Double.parseDouble(obj.getId()),obj.getVal());
                }
                p.sync();

            } catch (Exception e) {
                throw new RuntimeException("CACHE_PRIZE_LIST_ERROR_MSG");
            } finally {
//        	RedisPoolUtils.returnResourceObject(jedis);
            }
        }

        /**
         * 随机指定范围内N个不重复的数
         * 利用HashSet的特征，只能存放不同的值
         * @param min 指定范围最小值
         * @param max 指定范围最大值
         * @param HashSet<Integer> set 随机数结果集
         * @param arr 奖品列表
         * @return
         */
        public static HashSet<Prize> randomSet(int min, int max, HashSet<Prize> set, Prize[] arr) {

            for (Prize obj : arr) {

                // 排除无效奖品
                if (Integer.valueOf(String.valueOf(obj.getPoint())) > 0) {

                    // 被刺奖品的数量
                    int prizeNum = Integer.valueOf(String.valueOf(obj.getPrizeNum()));

                    // 记录本个奖品要停止生成的不重复的随机码的个数(hasHset数组大小 + 奖品个数)
                    int stopSize = set.size() + prizeNum;

                    // 生成指定建构数量的奖品唯一随机码
                    while (set.size() < stopSize) {
                        int num = (int) (Math.random() * (max - min)) + min;
                        set.add(new Prize(obj.getId(), num ));// 将不同的数存入HashSet中
                    }
                }
            }
            return set;
        }

        /**
         * 抽奖
         * @param lotteryId
         * @return
         */
        public static String luckDraw(String lotteryId,String engineerId) {
            String prizeId = null;
            try {
                if (jedis == null) {
                    jedis = jedisUtil.getJedis();
                }
                Long index = jedis.incr(lotteryId);

                Double id = jedis.zscore(PRIZE_LIST + lotteryId, index.intValue()+"");
                if(id != null){
                    prizeId = String.valueOf(id.intValue());

                    //设置每天
                    if(!jedis.exists(engineerId)){
                        jedis.incr(engineerId);
                        jedis.expire(engineerId, 60*60*24);
//						jedis.expire(engineerId, 30);
                    }else {
                        jedis.incr(engineerId);
                    }

                    //每个轮询的次数
                    jedis.incr(lotteryId + engineerId);

                    //删除缓存奖品
                    jedis.zrem(PRIZE_LIST + lotteryId, index+"");
                }

            } catch (Exception e) {
                throw new RuntimeException("CACHE_PRIZE_LIST_ERROR_MSG");
            } finally {
                // RedisPoolUtils.returnResourceObject(jedis);
            }
            return prizeId;
        }



        /**
         * 保存抽奖记录
         * @param lotteryId
         * @param lotteryManagement
         * @return
         */
        public static LotteryManagement saveLotteryManagement(String lotteryId, LotteryManagement lotteryManagement) {
            try {
                if (jedis == null) {
                    jedis = jedisUtil.getJedis();
                }
                int lotteryNum = Integer.valueOf(jedis.get(lotteryId));
                lotteryManagement.setLotteryNum(lotteryNum);

                int lotterySurplusNum = lotteryManagement.getLotteryTotailNum() - lotteryNum;
                lotteryManagement.setLotterySurplusNum(lotterySurplusNum);

                if(lotteryManagement.getLotteryTotailNum() <= lotteryNum){
                    lotteryManagement.setLotteryOver(1);
                }

            } catch (Exception e) {
                throw new RuntimeException("SAVE_LOTTERY_MANAGEMENT_ERROR_MSG");
            } finally {
                // RedisPoolUtils.returnResourceObject(jedis);
            }
            return lotteryManagement;
        }
    }

