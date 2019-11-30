package com.jt.common.service;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisSentinelPool;

@Service
public class RedisSentinelService {

    @Autowired
    private JedisSentinelPool jedisSentinelPool;

    public void set(String key, String value){
        Jedis jedis = jedisSentinelPool.getResource();
        jedis.set(key,value);
        jedis.close();
    }

    public String get(String key){
        Jedis jedis = jedisSentinelPool.getResource();
        String value = jedis.get(key);
        jedis.close();
        return value;
    }

}
