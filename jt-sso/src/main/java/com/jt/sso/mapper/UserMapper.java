package com.jt.sso.mapper;


import com.jt.common.mapper.SysMapper;
import com.jt.sso.pojo.User;
import org.apache.ibatis.annotations.Param;

public interface UserMapper extends SysMapper<User> {


    int findCheckUser(@Param("param") String param,@Param("column") String column);
}
