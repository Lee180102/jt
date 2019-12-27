package com.jt.cart.service;

import com.jt.cart.pojo.User;

public interface UserService {
    boolean findCheckUser(String param, int type);

    void saveUser(User user);

    String findUserByUsernameAndPassword(User user);
}
