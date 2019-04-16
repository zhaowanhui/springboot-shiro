package com.zwh.entity;

import lombok.Data;

import java.util.List;

@Data
public class Role {
    private Integer id;
    private String role_name;
    private List<Permission> permissions;
}
