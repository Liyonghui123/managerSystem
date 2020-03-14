package com.douyu.util;

import com.douyu.pojo.*;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;

import java.math.BigDecimal;
import java.util.*;

/**
 * @description:
 * @author: Dangerous
 * @time: 2020/3/11 16:23
 */
public class LotteryCommon {

    private static final String PRIZE_RULE = "prizeRule";
    public static final String PRIZE_LIST = "prizeList";


    /**
     * 计算总价值和中奖概率
     * @param arr  奖品数组
     * @param data  抽奖活动obj
     * @return
     */
    public static HashMap<String, Object> calculation(Prize[] arr , LotteryManagement data) {

        HashMap<String, Object> map =  new HashMap();

        //本次抽奖活动每次抽奖消耗能量豆、
        int lotteryPoint = data.getLotteryPoint();

        //本轮抽奖活动建构总价值能量豆
        int tailNum = 0;

        //本轮抽奖公司赞助能量豆
        int sponsorPoint = 0;

        //非赞助本轮抽奖总次数
        int lotteryTotailNumF = 0;

        //非赞助本轮抽奖总次数
        int lotteryTotailNumT = 0;

        //纪律本轮循环谢谢惠顾的下标
        int index = 0;

        //非赞助本轮抽奖总次数
        int notZeroPointPrizeNum = 0;

        for (int i = 0; i < arr.length; i++) {

            //单个奖品的数量
            int prizeNum = arr[i].getPrizeNum();

            lotteryTotailNumT +=prizeNum;

            //计算单个奖品总价值能量豆
            int a = prizeNum * arr[i].getPoint();

            //本轮次抽奖活动建构总价值能量豆
            tailNum += a;

            if (a>0) {
                //记录有效奖品数量
                notZeroPointPrizeNum+=prizeNum;
            } else {
                index = i;
            }

            arr[i].setPrizeTotailPoint(a);
        }
        //抽奖总次数
        lotteryTotailNumF = tailNum/lotteryPoint;

        //0非赞助1赞助
        if (0 == data.getLotteryType()) {
            //
            data.setLotteryTotailNum(lotteryTotailNumF);

            arr[index].setPrizeNum(lotteryTotailNumF - notZeroPointPrizeNum);
        } else {
            data.setLotteryTotailNum(lotteryTotailNumT);

            sponsorPoint = (lotteryTotailNumF - lotteryTotailNumT)*lotteryPoint;

        }

        //计算每个奖品的概率
        for (int i = 0; i < arr.length; i++) {

//			//单个奖品的数量
            int prizeNum = arr[i].getPrizeNum();

            //精确到小数点后4位
            BigDecimal d = new BigDecimal(prizeNum).divide(new BigDecimal(lotteryTotailNumT), 4, BigDecimal.ROUND_HALF_UP);

//			//本轮次抽奖活动建构总价值能量豆
            arr[i].setPrizeProbability(d);
        }

        data.setSponsorPoint(sponsorPoint);


        //初始化
        data.setLotteryName("0");
        data.setLotteryOver(0);
        data.setLotterySurplusNum(data.getLotteryTotailNum());

        //奖品数组
        map.put("arr", arr);

        //奖品活动
        map.put("data", data);

        //缓存建构数据

        cachePrizeListByLotteryId(data.getLotteryId(), data.getLotteryTotailNum(), arr);

        return map;
    }




    /**
     * 缓存奖品信息
     * @param lotteryId
     * @param sponsorPoint
     * @param arr
     */
    private static void cachePrizeListByLotteryId(String lotteryId,int sponsorPoint ,Prize[] arr) {
        Jedis jedis = JedisUtil.getJedis();
        try {
            //初始化本活动奖品抽奖次数
            jedis.set(lotteryId, "0");

            //奖品容器
            HashSet<Prize> set = new HashSet<>();

            randomSet(1, sponsorPoint, set, arr);

            Pipeline p = jedis.pipelined();

            //修改清除本次奖品列表
            p.del(PRIZE_LIST + lotteryId);

            for (Prize obj : set) {
                //缓存奖品随机码和奖品ID
                p.zadd(PRIZE_LIST + lotteryId, obj.getId(),obj.getVal().toString());
            }
            p.sync();

        } catch (Exception e) {
            throw new RuntimeException("CACHE_PRIZE_LIST_ERROR_MSG");
        } finally {
            JedisUtil.returnJedis(jedis);
        }
    }

    /**
     * 随机指定范围内N个不重复的数
     * 利用HashSet的特征，只能存放不同的值
     * @param min 指定范围最小值
     * @param max 指定范围最大值
     * @param set 随机数结果集
     * @param arr 奖品列表
     * @return
     */
    private static HashSet<Prize> randomSet(int min, int max, HashSet<Prize> set, Prize[] arr) {

        for (Prize obj : arr) {

            if (Integer.valueOf(String.valueOf(obj.getPoint())) > 0) {

                // 被刺奖品的数量
                int prizeNum = Integer.valueOf(String.valueOf(obj.getPrizeNum()));

                // 记录本个奖品要停止生成的不重复的随机码的个数(hasHset数组大小 + 奖品个数)
                int stopSize = set.size() + prizeNum;

                // 生成指定建构数量的奖品唯一随机码
                while (set.size() < stopSize) {
                    int num = (int) (Math.random() * (max - min)) + min;
                    // 将不同的数存入HashSet中
                    set.add(new Prize(obj.getId(), num ));
                }
            }
        }
        return set;
    }

    /**
     * 判断抽奖次数
     * @param arr
     * @param lev
     * @return
    LOTTERY_ID	USER_LEVEL	LIMIT_TIME
    1	        RMB大佬	        20
    2	        红钻	            15
    3	        橙钻	            13
    4	        黄钻          	11
    5	        绿钻	            9
    6	        青钻	            7
    7	        蓝钻	            5
    8	        紫钻	            3
    9	        平民	            1

    //S成功 E1已超过限制抽奖次数！E2 抽奖已结束,已达到抽奖次数。
     */
    public  static String verifyNum(List<LotteryLevel> arr , int lev , String engineerId , String lotteryId, int lotteryTotailNum) {
        String flag = "S";
        Jedis jedis = JedisUtil.getJedis();
        try{
            //验证抽奖活动是否存在
            if(jedis.get(lotteryId)!=null){

                //验证抽奖活次数是否达到结束上限
                if(Integer.valueOf(jedis.get(lotteryId)) >= lotteryTotailNum){
                    flag = "E2";
                    return  flag;
                }
            }
            if(arr != null){
                for (LotteryLevel rule : arr) {
                    if (rule.getLevelId() == lev) {
                        if (jedis.get(lotteryId+engineerId)!=null) {
                            int lunNum =Integer.valueOf(jedis.get(lotteryId+engineerId));
                            if(rule.getLimitTime()<= lunNum){
                                flag ="E1";
                            }
                        }

                    }
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("VERIFY_NUM__RULE_ERROR_MSG=======================>" + e.getMessage());
        } finally {
            JedisUtil.returnJedis(jedis);
        }
        return flag;
    }



    /**
     * 缓存抽奖规则
     * @param arr
     * @return
     */
    public static int ruleList(List<LotteryLevel>  arr ,String lotteryId ) {

        Jedis jedis = JedisUtil.getJedis();

        try {

            Pipeline p = jedis.pipelined();
            p.del(PRIZE_RULE+lotteryId);
            for (int i = 0; i < arr.size(); i++) {
                LotteryLevel rule = arr.get(i);
                p.sadd(PRIZE_RULE+lotteryId, PRIZE_RULE+lotteryId+rule.getUserLevel());
                p.set(PRIZE_RULE+lotteryId+rule.getUserLevel(), rule.toString());
            }
            p.sync();

        } catch (Exception e) {
            System.err.println("PRIZE_RULE_ERROR_MSG=======================>" + e.getMessage());
            return 0;
        } finally {
            JedisUtil.returnJedis(jedis);
        }
        return 1;
    }


    /**
     * 查询抽奖规则
     * @param lotteryId
     * @return
     */
    public  static LotteryLevel[] queryRuleList(String lotteryId) {

        LotteryLevel[] ruleList = null;
        Jedis jedis = JedisUtil.getJedis();
        try {
            Set<String> sets = jedis.smembers(PRIZE_RULE + lotteryId);
            Pipeline p = jedis.pipelined();
            for (String str: sets) {
                p.get(str);
            }
            List<Object> list= p.syncAndReturnAll();
            LotteryLevel rule;
            if(list.size()>0){
                ruleList = new LotteryLevel[list.size()];
                for (int i = 0; i < list.size(); i++) {
                    String str = list.get(i).toString();
                    String[] arr = str.substring(str.indexOf('(')+1, str.indexOf(')')).split(",");
                    rule = new LotteryLevel();
                    rule.setLevelId(Integer.valueOf(arr[0].split("=")[1]));
                    rule.setLotteryId(arr[1].split("=")[1]);
                    rule.setUserLevel(arr[2].split("=")[1]);
                    rule.setLimitTime(Integer.parseInt(arr[3].split("=")[1]));
                    ruleList[i]= rule;
                }
            }
        } catch (Exception e) {
            System.err.println("QUERY_PRIZE_RULE_ERROR_MSG=======================>" + e.getMessage());
            return null;
        } finally {
            JedisUtil.returnJedis(jedis);
        }
        return ruleList;
    }


    /**
     * 返回当前有效时间内的抽奖活动Id
     * @param arr
     * @return
     */
    public static HashMap<String, String> getLotteryId(LotteryManagement[] arr) {
        HashMap<String, String> map = new HashMap<>();
        Date date = new Date();
        for (LotteryManagement dataObject : arr) {
            if(date.after(dataObject.getStartDate()) && date.before(dataObject.getStopDate())){
                map.put("lotteryId", dataObject.getLotteryId());
                map.put("lotteryName", dataObject.getLotteryName());
                map.put("lotteryPoint", dataObject.getLotteryPoint().toString());
            }

        }
        return map;
    }
}
