package com.feedback.feedback_analytics.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.feedback.feedback_analytics.Service.FeedbackAnalysisService;

@RestController
public class FeedbackAnalysisController {
	
	@Autowired FeedbackAnalysisService feedbackAnalysisService;
	
}
