package com.jt.cart.mapper;


import com.jt.common.mapper.SysMapper;
import com.jt.cart.pojo.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface UserMapper extends SysMapper<User> {


    int findCheckUser(@Param("param") String param, @Param("column") String column);

    @Select("select * from tb_user where username = #{username} and password = #{password}")
    User findUserByUsernameAndPassword(User user);
}
