package com.tiktoklinkparsingtool.service;

import com.tiktoklinkparsingtool.mapper.UserMapper;
import com.tiktoklinkparsingtool.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author zgp
 * @Since 2021 -09 -11 01 :24
 * @Description 用户业务逻辑层
 */
@Service
public class UserService {

    @Autowired
    UserMapper userMapper;

    /**
     * 用户登录
     * @param username
     * @param password
     * @return
     */
    public List<Users> userLogin(String username, String password) {
        List<Users> usersList=userMapper.userLogin(username,password);
        return usersList;
    }
}
