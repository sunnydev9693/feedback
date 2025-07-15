package com.feedback.feedback_analytics.ExceptionHandler;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ErrorResponse {
	
	private LocalDateTime timeStamp;
	private String message;
	private String details;
	
}
