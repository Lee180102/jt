package com.jt.manage.controller;

import com.jt.manage.pojo.User;
import com.jt.manage.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserService userService;
    @RequestMapping("/userList")
    public String findAll(Model model) {
        List<User> userList = userService.findAll();
        model.addAttribute("userList", userList);
        return "userList";
    }
}
