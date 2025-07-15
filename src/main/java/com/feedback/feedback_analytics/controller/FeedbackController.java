package com.feedback.feedback_analytics.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.feedback.feedback_analytics.Service.FeedbackService;
import com.feedback.feedback_analytics.model.Feedback;
import com.feedback.feedback_analytics.util.ApiResponse;
import com.feedback.feedback_analytics.util.FeedbackDTO;

@RestController
@RequestMapping("/api/v1/feedback")
public class FeedbackController {
	
	private static final Logger logger = LogManager.getLogger(FeedbackController.class);
	@Autowired FeedbackService feedbackService;
	
	@PostMapping
	public ResponseEntity<ApiResponse<Feedback>> newFeedback(@RequestBody FeedbackDTO feedback){
		logger.info("Received request to add new feedback: {} ", feedback);
		return new ResponseEntity<>(new ApiResponse<Feedback>
		(feedbackService.addFeedback(feedback), true, "Feedback Added Successfully"),HttpStatus.CREATED);
	}
	
	@GetMapping
	public ResponseEntity<ApiResponse<Feedback>> getFeedback(@RequestParam("feedbackId") String feedbackId){
		logger.info("Received request to featch feedback: {}",feedbackId);
		return new ResponseEntity<>(new ApiResponse<Feedback>
		(feedbackService.featchFeedback(feedbackId), true, "Feedback fetched Successfully"),HttpStatus.OK);
	}
	
}
