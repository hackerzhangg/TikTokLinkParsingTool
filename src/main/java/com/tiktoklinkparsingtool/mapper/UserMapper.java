package com.tiktoklinkparsingtool.mapper;

import com.tiktoklinkparsingtool.model.Users;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author zgp
 * @Since 2021 -09 -11 01 :18
 * @Description 用户数据库层
 */
public interface UserMapper {

    /**
     * 用户登录
     * @param username
     * @param password
     * @return
     */
    List<Users> userLogin(@Param("username") String username, @Param("password") String password);
}
