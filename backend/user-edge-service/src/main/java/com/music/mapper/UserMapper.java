package com.music.mapper;

import com.music.domain.UserInfo;
import com.music.domain.UserStyleInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

/**
 * 用户的dao层接口
 */
@Component
public interface UserMapper {
    @Select("select id,username,password,email,age,gender,location from users where username = #{username}")
    UserInfo getUserByName(@Param("username") String username);

    @Insert("insert into users(username,password,email,age,gender,location) " +
            "values(#{u.username},#{u.password},#{u.email},#{u.age},#{u.gender},#{u.location})")
    void registerUser(@Param("u") UserInfo userInfo);

    @Insert("insert into user_style(uid,sid) values(#{u.uid},#{u.sid})")
    void setUserStyle(@Param("u") UserStyleInfo userStyle);

}
