package com.zwh.dao;

import com.zwh.entity.Role;
import com.zwh.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserDAO {
    User selectUser(@Param("username") String username);
    List<Role> selectRoles();
}
