package com.dionariao.security.config;

import org.springframework.boot.autoconfigure.couchbase.CouchbaseProperties.Authentication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.dionariao.security.authentication.UserAuthenticationFilter;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {


	private UserAuthenticationFilter userAuthenticationFilter;
	
	
	
	public SecurityConfiguration(UserAuthenticationFilter userAuthenticationFilter) {
		
		this.userAuthenticationFilter = userAuthenticationFilter;
	}

	public static final String [] ENDPOINTS_WITH_AUTHENTICATION_NOT_REQUIRED = {
			"/users/login", //url que usaremos para fazer login
            "/users",//url que usaremos para criar um usuário
            "/swagger-ui/**",
            "/v3/api-docs/**",
            "/swagger-resources/**",
            "/webjars/**"
	};
	
	public static final String [] ENDPOINTS_WITH_AUTHENTICATION_REQUIRED = {
			"/users/test",
			"/dio/**",
			"/frase/**",
			"/origem/**",
			"/palavra/**",
			"/significado/**"
	};
	
	public static final String [] ENDPOINTS_CUSTOMER = {
            "/users/test/customer"
    };
	
	public static final String [] ENDPOINTS_ADMIN = {
            "/users/test/administrator"
    };
	
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
		
		return httpSecurity.csrf().disable()
			   .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			   .and().authorizeHttpRequests()
			   .requestMatchers(ENDPOINTS_WITH_AUTHENTICATION_NOT_REQUIRED).permitAll()
	
			   .requestMatchers(ENDPOINTS_WITH_AUTHENTICATION_REQUIRED).authenticated()
			   .requestMatchers(ENDPOINTS_ADMIN).hasRole("ADMINISTRATOR")
			   .requestMatchers(ENDPOINTS_CUSTOMER).hasRole("CUSTOMER")
			   .anyRequest().denyAll()
			   .and().addFilterBefore(userAuthenticationFilter, UsernamePasswordAuthenticationFilter.class).build();
		
	}
	
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
		
		return authenticationConfiguration.getAuthenticationManager();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
