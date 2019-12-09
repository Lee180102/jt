package com.jt.manage.controller.web;

import com.alibaba.druid.util.StringUtils;
import com.jt.common.vo.SysResult;
import com.jt.manage.pojo.Item;
import com.jt.manage.pojo.ItemDesc;
import com.jt.manage.pojo.User;
import com.jt.manage.service.ItemService;
import com.jt.manage.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/web/item")
public class WebItemController {

    @Autowired
    private ItemService itemService;

    @Autowired
    private UserService userService;

    @RequestMapping("/findItemById")
    @ResponseBody
    public Item findItemById(Long itemId) {
        return itemService.findItemById(itemId);
    }


    @RequestMapping("/findItemDescById")
    @ResponseBody
    public ItemDesc findItemDescById(Long itemId) {
        return itemService.findItemDescById(itemId);
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

            cookie.setMaxAge(3600 * 24 *7);
            response.addCookie(cookie);
            return SysResult.oK();
        } catch (Exception e){
            e.printStackTrace();

        }
        return SysResult.build(201, "用户查询失败");
    }


}
