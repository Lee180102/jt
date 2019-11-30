package com.jt.manage.redis;

import org.junit.Test;
import org.junit.experimental.theories.suppliers.TestedOn;
import redis.clients.jedis.*;

import java.util.ArrayList;
import java.util.HashSet;
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

    @Test
    public void test02(){
        HashSet<String> sentinels = new HashSet<>();
        sentinels.add("192.168.31.181:26379");
        JedisSentinelPool sentinelPool = new JedisSentinelPool("mymaster", sentinels);

        Jedis jedis = sentinelPool.getResource();

        jedis.set("password","123456");

        System.out.println(jedis.get("password"));
    }

    @Test
    public void test03(){
        HashSet<HostAndPort> nodes = new HashSet<>();
        nodes.add(new HostAndPort("192.168.31.181",7000));
        nodes.add(new HostAndPort("192.168.31.181",7001));
        nodes.add(new HostAndPort("192.168.31.181",7002));
        nodes.add(new HostAndPort("192.168.31.181",7003));
        nodes.add(new HostAndPort("192.168.31.181",7004));
        nodes.add(new HostAndPort("192.168.31.181",7005));
        nodes.add(new HostAndPort("192.168.31.181",7006));
        nodes.add(new HostAndPort("192.168.31.181",7007));
        nodes.add(new HostAndPort("192.168.31.181",7008));

        JedisCluster jedisCluster = new JedisCluster(nodes);
        jedisCluster.set("456","456465");
        System.out.println(jedisCluster.get("456"));
    }
}
