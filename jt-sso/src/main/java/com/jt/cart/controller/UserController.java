package com.jt.cart.controller;

import com.alibaba.druid.util.StringUtils;
import com.jt.common.vo.SysResult;
import com.jt.cart.pojo.User;
import com.jt.cart.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import redis.clients.jedis.JedisCluster;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private JedisCluster jedisCluster;

    @RequestMapping("/check/{param}/{type}")
    @ResponseBody
    public MappingJacksonValue checkUser(@PathVariable String param, @PathVariable int type, String callback) {
        boolean flag = userService.findCheckUser(param, type);
        MappingJacksonValue jacksonValue = new MappingJacksonValue(SysResult.oK(flag));
        jacksonValue.setJsonpFunction(callback);
        System.out.println("查询：" + param);
        return jacksonValue;
    }

    @RequestMapping("/register")
    @ResponseBody
    public SysResult saveUser(User user) {
        try {
            userService.saveUser(user);
            System.out.println("register");
            return SysResult.oK();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return SysResult.build(201, "新增失败");
    }


    @RequestMapping("/login")
    @ResponseBody
    public SysResult login(User user) {
        try {
            String token = userService.findUserByUsernameAndPassword(user);
            if (StringUtils.isEmpty(token)) {
                return SysResult.build(201, "用户查询失败");
            }
            return SysResult.oK(token);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return SysResult.build(201, "用户查询失败");
    }


    @RequestMapping("/query/{token}")
    @ResponseBody
    public MappingJacksonValue findUserByTicket(@PathVariable String token, String callback) {

        String userJSON = jedisCluster.get(token);
        MappingJacksonValue jacksonValue = null;
        if (!StringUtils.isEmpty(userJSON)) {
            jacksonValue = new MappingJacksonValue(SysResult.oK(userJSON));
        } else {
            jacksonValue = new MappingJacksonValue(SysResult.build(201, "用户查询失败"));
        }
        jacksonValue.setJsonpFunction(callback);
        return jacksonValue;


    }


}
