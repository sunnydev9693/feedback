package com.feedback.feedback_analytics.util;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class ApiResponse<T> {
	
	private T data;
	private boolean sucess;
	private String message;
	private LocalDateTime timeStamp;
	
	public ApiResponse(T data, boolean sucess, String message) {
		super();
		this.data = data;
		this.sucess = sucess;
		this.message = message;
		this.timeStamp = LocalDateTime.now();
	}
	
}
