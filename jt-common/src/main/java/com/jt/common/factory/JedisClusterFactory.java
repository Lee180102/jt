package com.jt.common.factory;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.core.io.Resource;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPoolConfig;

import java.io.IOException;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

public class JedisClusterFactory implements FactoryBean<JedisCluster> {



    private JedisPoolConfig poolConfig;
    private String redisNodePrefix;
    private Resource propertySource;

    public Set<HostAndPort> getNodes(){
        HashSet<HostAndPort> nodes = new HashSet<>();
        try {
            Properties properties = new Properties();
            properties.load(propertySource.getInputStream());
            for (Object key :
                    properties.keySet()) {
                String strKey = (String) key;
                if (strKey.startsWith(redisNodePrefix)){
                    String value = properties.getProperty(strKey);
                    String[] args = value.split(":");
                    HostAndPort hostAndPort = new HostAndPort(args[0], Integer.parseInt(args[1]));
                    nodes.add(hostAndPort);

                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  nodes;
    }


    @Override
    public JedisCluster getObject() throws Exception {
        Set<HostAndPort> nodes = getNodes();
        JedisCluster jedisCluster = new JedisCluster(nodes, poolConfig);
        return jedisCluster;
    }

    @Override
    public Class<?> getObjectType() {
        return JedisCluster.class;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }

    public JedisPoolConfig getPoolConfig() {
        return poolConfig;
    }

    public void setPoolConfig(JedisPoolConfig poolConfig) {
        this.poolConfig = poolConfig;
    }

    public String getRedisNodePrefix() {
        return redisNodePrefix;
    }

    public void setRedisNodePrefix(String redisNodePrefix) {
        this.redisNodePrefix = redisNodePrefix;
    }

    public Resource getPropertySource() {
        return propertySource;
    }

    public void setPropertySource(Resource propertySource) {
        this.propertySource = propertySource;
    }


}
