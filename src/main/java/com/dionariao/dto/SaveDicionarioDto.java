package com.dionariao.dto;

import jakarta.validation.constraints.NotBlank;

public record SaveDicionarioDto (
		@NotBlank
		String name, 
		
		Long id
		
		) {}
