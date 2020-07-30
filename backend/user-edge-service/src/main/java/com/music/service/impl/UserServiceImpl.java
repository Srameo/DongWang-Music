package com.music.service.impl;

import com.music.domain.UserInfo;
import com.music.domain.UserStyleInfo;
import com.music.mapper.UserMapper;
import com.music.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService {
    @Override
    public String getUserNameById(int id) {
        return userMapper.getUserNameById(id);
    }

    @Resource
    private UserMapper userMapper;

    @Override
    public UserInfo getUserByName(String username) {
        return userMapper.getUserByName(username);
    }

    @Override
    public void registerUser(UserInfo userInfo) {
        userMapper.registerUser(userInfo);
    }

    @Override
    public void setUserStyle(UserStyleInfo userStyle) {
        userMapper.setUserStyle(userStyle);
    }
}
