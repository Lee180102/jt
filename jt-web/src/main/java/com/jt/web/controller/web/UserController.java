package com.jt.web.controller.web;


import com.alibaba.druid.util.StringUtils;
import com.jt.common.vo.SysResult;
import com.jt.web.pojo.User;
import com.jt.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import redis.clients.jedis.JedisCluster;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/user")
public class UserController {


    @Autowired
    private UserService userService;

    @Autowired
    private JedisCluster jedisCluster;

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


    @RequestMapping("/doLogin")
    @ResponseBody
    public SysResult doLogin(User user, HttpServletResponse response) {

        try {
            String token = userService.findUserByUsernameAndPassword(user);

            //判断登录是否有效
            if (StringUtils.isEmpty(token)) {
                return SysResult.build(201, "用户登录失败");
            }

            Cookie cookie = new Cookie("JT_TICKET", token);
            //cookie 保存路径，一般都是/
            cookie.setPath("/");

            /*
             * cookie的生命周期，单位秒
             * >0        生命的存活时间
             * =0        表示立即删除cookie
             * -1        表示会话结束删除cookie
             * */

            cookie.setMaxAge(3600 * 24 * 7);
            response.addCookie(cookie);
            return SysResult.oK();
        } catch (Exception e) {
            e.printStackTrace();

        }
        return SysResult.build(201, "用户查询失败");
    }

    @RequestMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {

        Cookie[] cookies = request.getCookies();
        String token = null;
        for (Cookie cookie : cookies) {
            if ("JT_TICKET".equals(cookie.getName())) {
                token = cookie.getValue();
                break;
            }
        }
        jedisCluster.del(token);
        Cookie cookie = new Cookie("JT_TICKET", "");

        cookie.setPath("/");
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        return "redirect:/index.html";

    }
}
