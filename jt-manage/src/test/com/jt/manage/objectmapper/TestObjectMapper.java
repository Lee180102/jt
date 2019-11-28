package com.jt.manage.objectmapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jt.manage.pojo.User;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestObjectMapper {

    @Test
    public void objectToJSON() throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        User user = new User();
        user.setId(1);
        user.setName("小明");
        user.setAge(18);
        user.setSex("男");

        String userJson = objectMapper.writeValueAsString(user);
        System.out.println(userJson);


        User user1 = objectMapper.readValue(userJson, User.class);
        System.out.println(user1.toString());

    }

    @Test
    public void listToJson() throws IOException {
        List<String> list = new ArrayList<>();
        list.add("username");
        list.add("password");
        list.add("accent");
        list.add("abc");
        ObjectMapper objectMapper = new ObjectMapper();
        String listJson = objectMapper.writeValueAsString(list);
        System.out.println(listJson);

        List list1 = objectMapper.readValue(listJson, List.class);
        System.out.println(list1);
    }

    @Test
    public void objectListToJson() throws IOException {
        List<User> userList = new ArrayList<>();
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

        userList.add(user0);
        userList.add(user1);
        userList.add(user2);

        ObjectMapper objectMapper = new ObjectMapper();
        String userListJson = objectMapper.writeValueAsString(userList);
        System.out.println(userListJson);

        List<User> list = objectMapper.readValue(userListJson, List.class);
        System.out.println(list);

        User[] users = objectMapper.readValue(userListJson, User[].class);
        List<User> users1 = Arrays.asList(users);
        System.out.println(users1);
    }

}
