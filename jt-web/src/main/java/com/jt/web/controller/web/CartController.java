package com.jt.web.controller.web;


import com.jt.web.pojo.Cart;
import com.jt.web.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


@Controller
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @RequestMapping("/show")
    public String findCartByUserId(Model model){
        Long userId = 7L;
        List<Cart> cartList = cartService.findCartByUserId(userId);
        model.addAttribute("cartList" ,cartList);
        return "cart";
    }
}
