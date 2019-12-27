package com.jt.cart.service;

import com.jt.cart.pojo.Cart;

import java.util.List;

public interface CartService {
    List<Cart> findCartByUserId(Long userId);
}
