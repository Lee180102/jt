package com.jt.web.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jt.common.service.HttpClientService;
import com.jt.common.vo.SysResult;
import com.jt.web.pojo.Cart;
import com.jt.web.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.PipedReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private HttpClientService httpClient;
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @Override
    public List<Cart> findCartByUserId(Long userId) {
        List<Cart> cartList = new ArrayList<>();


        String url = "http://cart.jt.com/cart/query/" + userId;

        try {
            String resultJson = httpClient.doGet(url);
            SysResult sysResult = OBJECT_MAPPER.readValue(resultJson, SysResult.class);
            if (sysResult.getStatus() == 200) {
                cartList = (List<Cart>) sysResult.getData();

            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }

        return cartList;
    }
}
