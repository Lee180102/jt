package com.jt.web.controller;


import com.jt.common.vo.SysResult;
import com.jt.web.pojo.User;
import com.jt.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/user")
public class UserController {


    @Autowired
    private UserService userService;


    @RequestMapping("/{moduleName}")
    public String index(@PathVariable String moduleName){
        return moduleName;
    }


    @RequestMapping("/doRegister")
    @ResponseBody
    public SysResult saveUser(User user){
        try {
            userService.saveUser(user);
            System.out.println("doRegister");
            return SysResult.oK();
        }catch (Exception e){
            e.printStackTrace();
        }
        return SysResult.build(201,"新增用户失败");
    }
}
