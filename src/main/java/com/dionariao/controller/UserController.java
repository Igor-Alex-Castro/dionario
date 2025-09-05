package com.dionariao.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dionariao.dto.CreateUserDto;
import com.dionariao.dto.LoginUserDto;
import com.dionariao.dto.RecoveryJwtTokenDto;
import com.dionariao.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

	private UserService userService;

	public UserController(UserService userService) {

		this.userService = userService;
	}

	@PostMapping("/login")
	public ResponseEntity<RecoveryJwtTokenDto> authenticaUser(@RequestBody LoginUserDto loginUserDto) {
		RecoveryJwtTokenDto token = userService.authenticateUser(loginUserDto);
		return new ResponseEntity<>(token, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<Void> createUser(@RequestBody CreateUserDto createUserDto) {
		userService.createUser(createUserDto);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	@GetMapping("/test")
	public ResponseEntity<String> getAuthenticationTest() {
		return new ResponseEntity<>("Autenticado com sucesso", HttpStatus.OK);
	}

	@GetMapping("/test/customer")
	public ResponseEntity<String> getCustomerAuthenticationTest() {

		return new ResponseEntity<>("Cliente autenticado com sucesso", HttpStatus.OK);
	}

	@GetMapping("/test/administrator")
	public ResponseEntity<String> getAdminAuthenticationTest() {
		return new ResponseEntity<>("Administrador autenticado com sucesso", HttpStatus.OK);
	}

}
