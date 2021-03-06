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

    @Select("SELECT count(uid) FROM stars WHERE uid=#{uid} AND mid=#{mid}")
    Integer staredOrNot(@Param("uid") int uid, @Param("mid") int mid);

    @Select("SELECT username FROM users WHERE id=#{id}")
    String getUserNameById(@Param("id") int id);

    @Select("select id,username,password,email,age,gender from users where username = #{username}")
    UserInfo getUserByName(@Param("username") String username);

    @Insert("insert into users(username,password,email,age,gender) " +
            "values(#{u.username},#{u.password},#{u.email},#{u.age},#{u.gender})")
    void registerUser(@Param("u") UserInfo userInfo);

    @Insert("insert into user_style(uid,sid) values(#{u.uid},#{u.sid})")
    void setUserStyle(@Param("u") UserStyleInfo userStyle);

}
