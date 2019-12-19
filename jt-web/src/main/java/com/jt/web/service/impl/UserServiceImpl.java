package com.jt.web.service.impl;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.jt.common.service.HttpClientService;
import com.jt.common.vo.SysResult;
import com.jt.web.pojo.User;
import com.jt.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private HttpClientService httpClient;

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @Override
    public void saveUser(User user) {
        String url = "http://sso.jt.com/user/register";

        HashMap<String, String> params = new HashMap<>();
        params.put("username",user.getUsername());
        params.put("password",user.getPassword());
        params.put("phone",user.getPhone());
        params.put("email",user.getEmail());

        try {
            String result = httpClient.doPost(url, params);
            SysResult sysResult = OBJECT_MAPPER.readValue(result, SysResult.class);
            if (sysResult.getStatus() != 200){
                throw new RuntimeException();
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }

    }

    @Override
    public String findUserByUsernameAndPassword(User user) {
        String token =null;
        String url = "http://sso.jt.com/user/login";
        HashMap<String, String> params = new HashMap<>();
        params.put("username", user.getUsername());
        params.put("password", user.getPassword());
        try {
            String resultJson = httpClient.doPost(url, params);
            SysResult sysResult = OBJECT_MAPPER.readValue(resultJson, SysResult.class);
            if (sysResult.getStatus() == 200){
                token = (String) sysResult.getData();

            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }

        return token;
    }
}
