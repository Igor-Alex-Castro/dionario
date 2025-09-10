package com.dionariao.handler;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.dionariao.exception.ResourceNotFoundException;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;


@RestControllerAdvice
public class GlobalExceptionHandler {

	// 400 Bad Request - Parâmetro ausente
	@ExceptionHandler(MissingServletRequestParameterException.class)
	public ResponseEntity<Map<String, Object>> handleMissingParams(MissingServletRequestParameterException ex, HttpServletRequest request){
		return ResponseEntity.badRequest().body(Map.of(
				"timestamp", LocalDateTime.now(),
				"status", HttpStatus.BAD_REQUEST,
				"error", "Parâmetro obrigatório não informado",
				"message", ex.getMessage(),
				"path", request.getRequestURI()
				));
				
	}
	
	 // 400 Bad Request - Parâmetro inválido / @NotBlank / @NotNull
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<Map<String, Object>> handleConstraintViolation(ConstraintViolationException ex, HttpServletRequest request){
		return ResponseEntity.badRequest().body(Map.of(
				"timestamp", LocalDateTime.now(),
				"status", HttpStatus.BAD_REQUEST,
				"error", "Parâmetro inválido",
				"message", ex.getMessage(),
				"path", request.getRequestURI()
				));
	}
	
	 @ExceptionHandler(MethodArgumentNotValidException.class)
	 public ResponseEntity<Map<String, Object>> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpServletRequest request) {
		 
		 String errors = ex.getBindingResult()
				 .getFieldErrors()
				 .stream()
				 .map(err -> err.getField() + ": " + err.getDefaultMessage())
				 .collect(Collectors.joining());
		 System.out.println(ex.getFieldError());
		 
		 return ResponseEntity.badRequest().body(Map.of(
					"timestamp", LocalDateTime.now(),
					"status", HttpStatus.BAD_REQUEST,
					"error", errors,
					"message", "DTO inválido" + ex.getMessage(),
					"path", request.getRequestURI()
					));
	 }
	 
	// 404 Not Found - Recurso não encontrado
	
	 @ExceptionHandler(ResourceNotFoundException.class)
	 public ResponseEntity<Map<String, Object>> handleResourceNodFound(ResourceNotFoundException ex, HttpServletRequest request){
		 return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of(
					"timestamp", LocalDateTime.now(),
					"status", HttpStatus.NOT_FOUND,
					"error", "Recurso não encontrado",
					"message", ex.getMessage(),
					"path", request.getRequestURI()
					));
	 }
	
}
