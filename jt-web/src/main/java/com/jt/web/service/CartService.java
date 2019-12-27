package com.jt.web.service;


import com.jt.web.pojo.Cart;

import java.util.List;

public interface CartService {
    List<Cart> findCartByUserId(Long userId);
}
