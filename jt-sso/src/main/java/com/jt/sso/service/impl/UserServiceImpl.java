package com.jt.sso.service.impl;

import com.jt.sso.mapper.UserMapper;
import com.jt.sso.pojo.User;
import com.jt.sso.service.UserService;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public boolean findCheckUser(String param, int type) {
        String column = null;
        switch (type){
            case 1:
                column = "username";break;
            case 2:
                column = "phone";break;
            case 3:
                column = "email";break;
        }

        int count = userMapper.findCheckUser(param,column);

        return count == 0 ? false : true ;
    }

    @Override
    public void saveUser(User user) {
        String md5Password = DigestUtils.md5Hex(user.getPassword());
        user.setPassword(md5Password);
        user.setEmail(user.getPhone());
        user.setCreated(new Date());
        user.setUpdated(user.getCreated());
        userMapper.insert(user);
    }
}
