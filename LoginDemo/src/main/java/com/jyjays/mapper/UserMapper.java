package com.jyjays.mapper;

import com.jyjays.pojo.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserMapper {
    User selectUser(User user);
    User selectName(String username);
//    @Select("select * from tb_user1 where username=#{username} and password=#{password}")
//    User select(@Param("username") String username,@Param("password") String password);
   void addUser(User user);
}
