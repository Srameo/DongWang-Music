package com.music.mapper;

import com.music.domain.UserInfo;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.servlet.handler.UserRoleAuthorizationInterceptor;

import javax.annotation.Resource;

@SpringBootTest
public class TestUserMapper {

    @Resource
    private UserMapper userMapper;

    @Test
    public void getUserByNameTest(){
        String username = "lisi";
        System.out.println(userMapper.getUserByName(username));
    }

    @Test
    public void registerUser(){
        UserInfo userInfo = new UserInfo();
        userInfo.setUsername("zhangsan");
        userInfo.setPassword("zhangsan");
        userInfo.setEmail("zhangsan@email.com");
        userInfo.setAge(20);
        userInfo.setGender(0);
        userMapper.registerUser(userInfo);
    }

    @Test
    public void testStaredOrNot() {
        System.out.println(userMapper.staredOrNot(5, 60008));
        System.out.println(userMapper.staredOrNot(5, 1));
    }

}
