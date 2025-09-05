package com.dionariao.security.authentication;

import java.io.IOException;
import java.net.Authenticator;
import java.util.Arrays;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import com.dionariao.model.User;
import com.dionariao.repository.UserRepository;
import com.dionariao.security.config.SecurityConfiguration;
import com.dionariao.security.userdetails.UserDetailsImpl;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class UserAuthenticationFilter extends OncePerRequestFilter {

	private JwtTokenService jwtTokenService;

	private UserRepository userRepository;

	public UserAuthenticationFilter(JwtTokenService jwtTokenService, UserRepository userRepository) {
		// TODO Auto-generated constructor stub

		this.jwtTokenService = jwtTokenService;
		this.userRepository = userRepository;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		if (!checkIfEndPointIsPublic(request)) {
			String token = recoveryToken(request);
			if (token != null) {
				String subject = jwtTokenService.getSubjectFromToken(token);
				User user = userRepository.findByEmail(subject).get();
				UserDetailsImpl userDetails = new UserDetailsImpl(user);

				Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails.getUsername(), null,
						userDetails.getAuthorities());

				SecurityContextHolder.getContext().setAuthentication(authentication);
			} else {
				throw new RuntimeException("O token esta ausente");
			}

		}
		filterChain.doFilter(request, response);
	}

	private String recoveryToken(HttpServletRequest request) {
		// TODO Auto-generated method stub
		String authorizationHeader = request.getHeader("Authorization");
		if (authorizationHeader != null) {
			return authorizationHeader.replace("Bearer ", "");
		}

		return null;
	}

	private boolean checkIfEndPointIsPublic(HttpServletRequest request) {
		// TODO Auto-generated method stub
		String requestURI = request.getRequestURI();

		AntPathMatcher pathMatcher = new AntPathMatcher();
		
		for(String pattern : SecurityConfiguration.ENDPOINTS_WITH_AUTHENTICATION_NOT_REQUIRED) {
			if(pathMatcher.match(pattern, requestURI)) {
				return true;
			}
		}
		
		return false;
		
	}

}
