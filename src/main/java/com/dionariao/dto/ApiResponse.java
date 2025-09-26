package com.dionariao.dto;

import java.time.LocalDateTime;
import java.util.List;

public class ApiResponse<T> {

	private LocalDateTime timestamp;
	private int status;
	private String message;
	private T data;

	public ApiResponse(int status, String message, T data) {
		// TODO Auto-generated constructor stub
		this.timestamp = LocalDateTime.now();
		this.status = status;
		this.message = message;
		this.data = data;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	
	
}
