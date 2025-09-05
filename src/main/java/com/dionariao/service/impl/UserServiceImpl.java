package com.dionariao.service.impl;

import java.util.List;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.dionariao.dto.CreateUserDto;
import com.dionariao.dto.LoginUserDto;
import com.dionariao.dto.RecoveryJwtTokenDto;
import com.dionariao.model.Role;
import com.dionariao.model.User;
import com.dionariao.repository.UserRepository;
import com.dionariao.security.authentication.JwtTokenService;
import com.dionariao.security.userdetails.UserDetailsImpl;
import com.dionariao.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	private AuthenticationManager authenticationManager;

 
    private JwtTokenService jwtTokenService;

    
    private UserRepository userRepository;

    
    private PasswordEncoder passwordEncoder;
	
	

	
	



	public UserServiceImpl(AuthenticationManager authenticationManager, JwtTokenService jwtTokenService,
			UserRepository userRepository, PasswordEncoder passwordEncoder) {
		
		this.authenticationManager = authenticationManager;
		this.jwtTokenService = jwtTokenService;
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
	}



	@Override
	public RecoveryJwtTokenDto authenticateUser(LoginUserDto loginUserDto) {
		// TODO Auto-generated method stub
		UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
				new UsernamePasswordAuthenticationToken(loginUserDto.email(), loginUserDto.password());
		
		
		Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
		
		UserDetailsImpl userDetailsImpl = (UserDetailsImpl) authentication.getPrincipal();
		
		
		
		return new RecoveryJwtTokenDto(jwtTokenService.generateToken(userDetailsImpl));
	}

	@Override
	public void createUser(CreateUserDto createUserDto) {
		
		/*
		 * User newUser = User.builder() .email(createUserDto.email())
		 * .password(securityConfiguration.passwordEncoder().encode(createUserDto.
		 * password()))
		 * .roles(List.of(Role.builder().name(createUserDto.role()).build())) .build();
		 */
		
		
		 // Cria um novo usuário com os dados fornecidos
        //securityConfiguration.passwordEncoder().encode(createUserDto.password())
		
		User newUser = User.builder()
                .email(createUserDto.email())
                // Codifica a senha do usuário com o algoritmo bcrypt
                .password(passwordEncoder.encode(createUserDto.password()))
                // Atribui ao usuário uma permissão específica
                .roles(List.of(Role.builder().name(createUserDto.role()).build()))
                .build();

        // Salva o novo usuário no banco de dados
        userRepository.save(newUser);
        
		
		
	}

}
