package com.jt.manage.redis;

import com.alibaba.druid.util.StringUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jt.manage.pojo.User;
import org.junit.Test;
import redis.clients.jedis.Jedis;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TestRedis {


    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    //String类型
    @Test
    public void test01() {
        Jedis jedis = new Jedis("192.168.31.181", 6379);
        jedis.set("username", "admin");
        System.out.println(jedis.get("username"));

    }

    //Hash类型
    @Test
    public void test02() {
        Jedis jedis = new Jedis("192.168.31.181", 6379);
        jedis.hset("user", "id", "1");
        jedis.hset("user", "name", "小明");
        jedis.hset("user", "age", "18");
        Map<String, String> user = jedis.hgetAll("user");
        System.out.println(user);
    }

    //List类型
    @Test
    public void test03() {
        Jedis jedis = new Jedis("192.168.31.181", 6379);
        jedis.lpush("list", "1,2,3,4,5", "6", "7");
        System.out.println(jedis.lpop("list"));
        System.out.println(jedis.lpop("list"));
        System.out.println(jedis.rpop("list"));
    }


    @Test
    public void test04(){
        Jedis jedis = new Jedis("192.168.31.181", 6379);
        String username = "user02";
        String key = "USER_NAME_" + username;
        String result = jedis.get(key);
        List<User> userList;

        //模拟数据库数据
        List<User> data = new ArrayList<>();
        User user0 = new User();
        user0.setId(1);
        user0.setName("小明");
        user0.setAge(18);
        user0.setSex("男");
        User user1 = new User();
        user1.setId(1);
        user1.setName("小明");
        user1.setAge(18);
        user1.setSex("男");
        User user2 = new User();
        user2.setId(1);
        user2.setName("小明");
        user2.setAge(18);
        user2.setSex("男");


        data.add(user0);
        data.add(user1);
        data.add(user2);

        try {
            if (StringUtils.isEmpty(result)){

                userList = data;
                String userListJson = OBJECT_MAPPER.writeValueAsString(userList);
                jedis.set(key,userListJson);
                System.out.println("查询数据库");
                System.out.println(userList);
            }else {
              userList =  OBJECT_MAPPER.readValue(result, List.class);
              System.out.println("查询缓存");
              System.out.println(userList);
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
