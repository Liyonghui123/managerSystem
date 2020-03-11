package com.douyu.util;

import com.douyu.pojo.LotteryManagement;
import com.douyu.pojo.Prize;
import com.douyu.pojo.Rule;
import com.douyu.pojo.WinningRecord;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    private static JedisUtil jedisUtil;

    public static final String PRIZE_RULE = "prizeRule";

    public static Jedis jedis = jedisUtil.getJedis();

    /**
     * 计算总价值和中奖概率
     * @param arr  奖品数组
     * @param data  抽奖活动obj
     * @return
     */
    public static HashMap<String, Object> calculation(Prize[] arr , LotteryManagement data) {

        HashMap<String, Object> map =  new HashMap<String, Object>();

        //本次抽奖活动每次抽奖消耗能量豆、
        int lotteryPoint = data.getLotteryPoint();

        //本轮抽奖活动建构总价值能量豆
        int tailNum = 0;

        //本轮抽奖公司赞助能量豆
        int sponsorPoint = 0;

        //非赞助本轮抽奖总次数
        int lotteryTotailNum_F = 0;

        //非赞助本轮抽奖总次数
        int lotteryTotailNum_T = 0;

        //纪律本轮循环谢谢惠顾的下标
        int index = 0;

        //非赞助本轮抽奖总次数
        int NotZeroPointPrizeNum = 0;

        for (int i = 0; i < arr.length; i++) {

            //单个奖品的数量
            int prizeNum = arr[i].getPrizeNum();

            lotteryTotailNum_T +=prizeNum;

            //计算单个奖品总价值能量豆
            int a = prizeNum * arr[i].getPoint();

            //本轮次抽奖活动建构总价值能量豆
            tailNum += a;

            if (a>0) {
                //纪律有效间奖品数量
                NotZeroPointPrizeNum+=prizeNum;
            } else {
                index = i;
            }

            arr[i].setPrizeTotailPoint(a);
        }

        lotteryTotailNum_F = tailNum/lotteryPoint;

        //0非赞助1赞助
        if (0 == data.getLotteryType()) {
            //
            data.setLotteryTotailNum(lotteryTotailNum_F);

            arr[index].setPrizeNum(lotteryTotailNum_F - NotZeroPointPrizeNum);
        } else {
            data.setLotteryTotailNum(lotteryTotailNum_T);

            sponsorPoint = (lotteryTotailNum_F - lotteryTotailNum_T)*lotteryPoint;

        }

        //计算每个奖品的概率
        for (int i = 0; i < arr.length; i++) {

//			//单个奖品的数量
            int prizeNum = arr[i].getPrizeNum();

            //精确到小数点后4位
            BigDecimal d = new BigDecimal(prizeNum).divide(new BigDecimal(lotteryTotailNum_T), 4, BigDecimal.ROUND_HALF_UP);

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
        CacheLottery.cachePrizeListByLotteryId(data.getLotteryId(), data.getLotteryTotailNum(), arr);

        return map;
    }

    /**
     * 缓存抽奖规则
     * @param arr
     * @return
     */
    public static int ruleList(Rule[] arr ,String lotteryId ) {
        if(jedis == null){
            jedis = jedisUtil.getJedis();
        }
        try {

            Pipeline p = jedis.pipelined();
            p.del(PRIZE_RULE+lotteryId);
            for (int i = 0; i < arr.length; i++) {
                Rule rule = arr[i];
                p.sadd(PRIZE_RULE+lotteryId, PRIZE_RULE+lotteryId+rule.getEngineerLevel());
                p.set(PRIZE_RULE+lotteryId+rule.getEngineerLevel(), rule.toString());
            }
            p.sync();

        } catch (Exception e) {
            System.err.println("PRIZE_RULE_ERROR_MSG=======================>" + e.getMessage());
            return 0;
        } finally {
//        	RedisPoolUtils.returnResourceObject(jedis);
        }
        return 1;
    }


    /**
     * 查询抽奖规则
     * @param lotteryId
     * @return
     */
    public static Rule[] queryRuleList(String lotteryId) {

        Rule[] ruleList = null;
        try {
            if(jedis == null){
                jedis = jedisUtil.getJedis();
            }
            Set<String> sets = jedis.smembers(PRIZE_RULE + lotteryId);
            Pipeline p = jedis.pipelined();
            for (String str: sets) {
                p.get(str);
            }
            List<Object> list= p.syncAndReturnAll();
            Rule rule = null;
            if(list.size()>0){
                ruleList = new Rule[list.size()];
                for (int i = 0; i < list.size(); i++) {
                    String str = list.get(i).toString();
                    String[] arr = str.substring(str.indexOf('[')+1, str.indexOf(']')).split(",");
                    rule = new Rule();
                    rule.setEngineerLevel(Integer.valueOf(arr[0].split("=")[1]));
                    rule.setRestrictionType(Integer.valueOf(arr[1].split("=")[1]));
                    rule.setNum(Integer.valueOf(arr[2].split("=")[1]));
                    ruleList[i]= rule;
                }
            }
        } catch (Exception e) {
            System.err.println("QUERY_PRIZE_RULE_ERROR_MSG=======================>" + e.getMessage());
            return null;
        } finally {
//        	RedisPoolUtils.returnResourceObject(jedis);
        }
        return ruleList;
    }


    /**
     * 返回当前有效时间内的抽奖活动Id
     * @param arr
     * @return
     */
    public static HashMap<String, String> getLotteryId(LotteryManagement[] arr) {
        HashMap<String, String> map = new HashMap<String, String>();
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



    /**
     * 判断抽奖次数
     * @param arr
     * @param lev
     * @return
    {id:"1", text:"其他"},
    {id:"2", text:"达人"},
    {id:"3", text:"宗师"},
    {id:"4", text:"师尊"},
    {id:"5", text:"大师"},
    {id:"6", text:"师傅"}
    var restrictionType = [
    {id:"0", text:"每天"},
    {id:"1", text:"每轮"},
    ];
     */
    public static String verifyNum(Rule[] arr ,int lev , String engineerId ,String lotteryId,int lotteryTotailNum) {

//		System.out.println("============>Rule:" + arr.length);
//		System.out.println("============>lev:" + lev);
//		System.out.println("============>engineerId:" + engineerId);
//		System.out.println("============>lotteryId:" + lotteryId);
//		System.out.println("============>lotteryTotailNum:" + lotteryTotailNum);
        //S成功 E1已超过限制抽奖次数！E2 抽奖已结束,已达到抽奖次数。
        String flag = "S";

        try {
            if(jedis == null){
                jedis = jedisUtil.getJedis();
            }

            //验证抽奖活动是否存在
            if(jedis.get(lotteryId)!=null){

                //验证抽奖活次数是否达到结束上限
                if(Integer.valueOf(jedis.get(lotteryId)) >= lotteryTotailNum){
                    return flag = "E2" ;
                }
            }
            if(arr != null){
                for (Rule rule : arr) {
                    if (rule.getEngineerLevel() == lev) {

                        //验证用户lev对应的抽奖次数是否消耗完毕  0:每天  , 1:每轮
                        if (rule.getRestrictionType() == 0) {
                            if (jedis.get(engineerId)!=null) {
                                int lunNum =Integer.valueOf(jedis.get(engineerId));
                                if(rule.getNum()<= lunNum)flag ="E1";
                            }
                        } else {
                            if (jedis.get(lotteryId+engineerId)!=null) {
                                int lunNum =Integer.valueOf(jedis.get(lotteryId+engineerId));
                                if(rule.getNum()<= lunNum)flag ="E1";
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("VERIFY_NUM__RULE_ERROR_MSG=======================>" + e.getMessage());
        } finally {
//        	RedisPoolUtils.returnResourceObject(jedis);
        }
        return flag;
    }



    /**
     * 变更符合过期时间的奖品
     * @param data
     * @param day
     * @return
     */
    public static WinningRecord[] winningBeOverdue(WinningRecord[] data , int day ) {
        Calendar ca = Calendar.getInstance();
        // num为增加的天数，可以改变的
        ca.add(Calendar.DATE, -day);
        Date dateAfter = ca.getTime();
        for (int i = 0; i < data.length; i++) {
            Date date = data[i].getCreateTime();
            if(dateAfter.after(date)){
                data[i].setField2("2");
            }
        }
        return data;
    }

}
