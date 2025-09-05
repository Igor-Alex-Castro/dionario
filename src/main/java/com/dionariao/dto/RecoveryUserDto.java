package com.dionariao.dto;

import java.util.List;

import com.dionariao.model.Role;

public record RecoveryUserDto(
		
		 Long id,
	        String email,
	        List<Role> roles
	        ) {

}
