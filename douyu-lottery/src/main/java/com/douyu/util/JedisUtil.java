package com.douyu.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * @description:
 * @author: Dangerous
 * @time: 2020/3/10 12:33
 */
@Component
public  class JedisUtil {
    @Autowired
    private JedisPool jedisPool;

    /**
     * 获取jedis
     * @return
     */
    public Jedis getJedis(){
        Jedis jedis = jedisPool.getResource();
        return jedis;
    }
    public void returnJedis(Jedis jedis){
        jedis.close();
    }
    /**
     * 清空redis
     * @return
     */
    public String flushAll() {
        Jedis jedis = jedisPool.getResource();
        try{
            return jedis.flushAll();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            jedis.close();
        }
        return null;

    }
    /**
     * 存储字符串键值对
     * @param key
     * @param value
     * @return
     * @author hw
     * @date 2018年12月14日
     */
    public String set(String key, String value) {
        Jedis jedis = jedisPool.getResource();
        try {
            return jedis.set(key, value);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            jedis.close();
        }
        return null;
    }

    /**
     * 得到对应键的字符串值
     * @param key
     * @return
     * @author hw
     * @date 2018年12月14日
     */
    public String get(String key) {
        Jedis jedis = jedisPool.getResource();
        try {
            return jedis.get(key);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            jedis.close();
        }
        return null;
    }

    /**
     * 删除字符串键值对
     * @param keys
     * @return
     * @author hw
     * @date 2018年12月14日
     */
    public Long del(String... keys) {
        Jedis jedis = jedisPool.getResource();
        try {
            return jedis.del(keys);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            jedis.close();
        }
        return null;
    }

    /**
     * 存储对象
     * @param key
     * @param value
     * @return
     * @author hw
     * @date 2018年12月14日
     */
    public String setObject(String key, Object value) {
        Jedis jedis = jedisPool.getResource();
        try {
            return jedis.set(key.getBytes(), ObjectUtil.serialize(value));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            jedis.close();
        }
        return null;
    }

    /**
     * 得到对应键的对象
     * @param key
     * @return
     * @author hw
     * @date 2018年12月14日
     */
    public Object getObject(String key) {
        Jedis jedis = jedisPool.getResource();
        try {
            byte[] byteArr =  jedis.get(key.getBytes());
            return ObjectUtil.unSerialize(byteArr);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            jedis.close();
        }
        return null;
    }



}
