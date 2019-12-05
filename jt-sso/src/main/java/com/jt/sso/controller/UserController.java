package com.jt.sso.controller;

import com.jt.common.vo.SysResult;
import com.jt.sso.pojo.User;
import com.jt.sso.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/check/{param}/{type}")
    @ResponseBody
    public MappingJacksonValue checkUser(@PathVariable String param, @PathVariable int type, String callback){
        boolean flag =  userService.findCheckUser(param, type);
        MappingJacksonValue jacksonValue = new MappingJacksonValue(SysResult.oK(flag));
        jacksonValue.setJsonpFunction(callback);
        System.out.println("查询：" + param);
        return jacksonValue;
    }

    @RequestMapping("/register")
    @ResponseBody
    public SysResult saveUser(User user){
        try {
            userService.saveUser(user);
            System.out.println("register");
            return SysResult.oK();
        }catch (Exception e){
            e.printStackTrace();
        }
        return SysResult.build(201,"新增失败");
    }

}
