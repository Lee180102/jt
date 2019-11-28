package com.jt.manage.redis;

import org.junit.Test;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

import java.util.ArrayList;
import java.util.List;

public class TestRedisSentinel {
    @Test
    public void test01(){
        //定义连接池大小
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(1000);
        jedisPoolConfig.setMaxIdle(100);
        jedisPoolConfig.setTestOnBorrow(true);

        //定义分片的List集合
        List<JedisShardInfo> shardInfoList = new ArrayList<>();
        shardInfoList.add(new JedisShardInfo("192.168.31.181",6379));
        shardInfoList.add(new JedisShardInfo("192.168.31.181",6380));
        shardInfoList.add(new JedisShardInfo("192.168.31.181",6381));

        //创建分片的对象
        ShardedJedisPool shardedJedisPool = new ShardedJedisPool(jedisPoolConfig, shardInfoList);
        ShardedJedis shardedJedis = shardedJedisPool.getResource();
        shardedJedis.set("username","admin");
        System.out.println("获取数据" + shardedJedis.get("username"));
        shardedJedis.close();
    }
}
