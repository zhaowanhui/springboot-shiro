package com.zwh.dao;

import com.zwh.entity.User;
import org.apache.ibatis.annotations.Param;

public interface UserDAO {
    User selectUser(@Param("username") String username);
}
