package com.jt.cart.controller;

import com.jt.cart.pojo.Cart;
import com.jt.cart.service.CartService;
import com.jt.common.vo.SysResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/cart")
public class CartController  {

    @Autowired
    private CartService cartService;

    @RequestMapping("/query/{userId}")
    @ResponseBody
    public SysResult findCartByUserId(@PathVariable Long userId){


        try {
            List<Cart> cartList = cartService.findCartByUserId(userId);
            return SysResult.oK(cartList);
        }catch (Exception e){
            e.printStackTrace();

        }
        return SysResult.build(201 ,"购物车查询失败");
    }
}
