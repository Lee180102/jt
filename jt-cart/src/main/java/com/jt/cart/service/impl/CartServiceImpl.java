package com.jt.cart.service.impl;

import com.jt.cart.mapper.CartMapper;
import com.jt.cart.pojo.Cart;
import com.jt.cart.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartMapper cartMapper;

    @Override
    public List<Cart> findCartByUserId(Long userId) {
        Cart cart = new Cart();
        cart.setUserId(userId);
        return cartMapper.select(cart);
    }
}
