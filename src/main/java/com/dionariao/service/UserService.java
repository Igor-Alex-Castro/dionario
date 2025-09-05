package com.dionariao.service;

import com.dionariao.dto.CreateUserDto;
import com.dionariao.dto.LoginUserDto;
import com.dionariao.dto.RecoveryJwtTokenDto;

public interface  UserService {

	public RecoveryJwtTokenDto authenticateUser(LoginUserDto loginUserDto);
	
	public void createUser(CreateUserDto createUserDto);
	
	

}
