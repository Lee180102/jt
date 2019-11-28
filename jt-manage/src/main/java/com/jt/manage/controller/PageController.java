package com.jt.manage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller

public class PageController {


    @RequestMapping("/page/{module}")
    public String module(@PathVariable String  module){
        return module;
    }

}
