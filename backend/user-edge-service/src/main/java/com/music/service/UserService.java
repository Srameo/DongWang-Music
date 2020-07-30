package com.music.service;

import com.music.domain.UserInfo;
import com.music.domain.UserStyleInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

/**
 * 用户的业务层接口
 */
public interface UserService {
    boolean staredOrNot(int uid, int mid);
    String getUserNameById(int id);
    UserInfo getUserByName(String username);
    void registerUser(UserInfo userInfo);
    void setUserStyle(UserStyleInfo userStyle);
}
