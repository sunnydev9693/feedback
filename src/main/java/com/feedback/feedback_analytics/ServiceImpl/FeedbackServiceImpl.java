package com.feedback.feedback_analytics.ServiceImpl;

import java.io.IOException;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.feedback.feedback_analytics.ExceptionHandler.NotFoundException;
import com.feedback.feedback_analytics.Service.FeedbackService;
import com.feedback.feedback_analytics.model.Feedback;
import com.feedback.feedback_analytics.model.User;
import com.feedback.feedback_analytics.repository.FeedbackRepo;
import com.feedback.feedback_analytics.repository.UserRepo;
import com.feedback.feedback_analytics.util.FeedbackDTO;

@Service
public class FeedbackServiceImpl implements FeedbackService{
	
	@Autowired FeedbackRepo feedbackRepo;
	@Autowired UserRepo userRepo;
	@Autowired private ObjectMapper objectMapper;
	
	@Override
	public Feedback addFeedback(FeedbackDTO feedback) {
		User user = userRepo.findByUserName(feedback.getUserName())
				.orElseThrow(() -> new NotFoundException("User Not Found."));
		Feedback newFeedback = new Feedback();
		newFeedback.setUser(user);
		newFeedback.setDepartment(feedback.getDepartment());
		newFeedback.setFeedbackType(feedback.getFeedbackType());
		newFeedback.setMessage(feedback.getMessage());
		newFeedback.setSentiment(feedback.getSentiment());
		
		try {
			newFeedback.setKeyword(objectMapper.writeValueAsString(feedback.getKeywords()));
		} catch (JsonProcessingException e) {
			throw new RuntimeException("Unable to process feedback keywords JSON", e);
		}
		newFeedback.setFeedbackId(UUID.randomUUID());
		
		Feedback addFeedback =  feedbackRepo.save(newFeedback);
		
		if (feedback != null) {
	        try {
	            Map<String, String> keywords = objectMapper.readValue(
	            	addFeedback.getKeyword(), new TypeReference<Map<String, String>>() {});
	            	addFeedback.setKeywords(keywords);
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
		
		return addFeedback;
	}

	@Override
	public Feedback featchFeedback(String feedbackId) {
		Feedback feedback = feedbackRepo.findById(UUID.fromString(feedbackId)).orElse(null);
		if (feedback != null) {
	        try {
	            Map<String, String> keywords = objectMapper.readValue(
	                feedback.getKeyword(), new TypeReference<Map<String, String>>() {});
	            feedback.setKeywords(keywords);
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
		return feedback;
	}
	
}








