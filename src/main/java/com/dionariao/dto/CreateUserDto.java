package com.dionariao.dto;

import com.dionariao.enums.RolesName;

public record CreateUserDto( String email,
        String password,
        RolesName role) {

}
