package com.jt.web.service;

import com.jt.web.pojo.User;

public interface UserService {

    void saveUser(User user);

    String findUserByUsernameAndPassword(User user);
}
