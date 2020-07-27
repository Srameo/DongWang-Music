package com.music.service;

import com.music.domain.UserStyleInfo;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
public class TestUserService {

    @Resource
    private UserService userService;

    @Test
    public void getUserByNameTest(){
        System.out.println(userService.getUserByName("zhangsan"));
    }

    @Test void setUserStyleTest(){
        UserStyleInfo userStyleInfo = new UserStyleInfo(1, 1);
        userService.setUserStyle(userStyleInfo);
    }

}
