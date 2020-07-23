package com.music.service;

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

}
