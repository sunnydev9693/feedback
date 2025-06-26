package com.feedback.feedback_analytics.util;

import java.util.Map;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

@Data
public class FeedbackDTO {
	
	private String department;
	
	private String feedbackType;
	
	private String message;
	
	@Enumerated(EnumType.STRING)
	private Sentiment sentiment;
	
    private Map<String, String> keywords;
	
	private String userName;
	
}
