package com.feedback.feedback_analytics.ServiceImpl;

import java.io.IOException;
import java.util.Map;
import java.util.UUID;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.feedback.feedback_analytics.ExceptionHandler.NotFoundException;
import com.feedback.feedback_analytics.Service.FeedbackService;
import com.feedback.feedback_analytics.kafka.KafkaProducerService;
import com.feedback.feedback_analytics.model.Feedback;
import com.feedback.feedback_analytics.model.User;
import com.feedback.feedback_analytics.repository.FeedbackRepo;
import com.feedback.feedback_analytics.repository.UserRepo;
import com.feedback.feedback_analytics.util.FeedbackDTO;

@Service
public class FeedbackServiceImpl implements FeedbackService{
	
	private static final Logger logger = LogManager.getLogger(FeedbackServiceImpl.class);
	@Autowired private FeedbackRepo feedbackRepo;
	@Autowired private UserRepo userRepo;
	@Autowired private ObjectMapper objectMapper;
	@Autowired private KafkaProducerService producerService;
	
	@Override
	public Feedback addFeedback(FeedbackDTO feedback) {
		
		logger.info("Checking User Exist or not: {}", feedback);
		User user = userRepo.findByUserName(feedback.getUserName())
				.orElseThrow(() -> new NotFoundException("User Not Found."));
		
		Feedback newFeedback = new Feedback();
		newFeedback.setUser(user);
		newFeedback.setDepartment(feedback.getDepartment());
		newFeedback.setFeedbackType(feedback.getFeedbackType());
		newFeedback.setMessage(feedback.getMessage());
		newFeedback.setSentiment(null);
		
		newFeedback.setFeedbackId(UUID.randomUUID());
		
		logger.info("Adding Feedback record: {}", feedback);
		Feedback addFeedback =  feedbackRepo.save(newFeedback);
		
		//call kafka
        try {
        	String feedbackJson = null;
        	logger.info("Prepraing JSON object for Kafka");
			feedbackJson = objectMapper.writeValueAsString(addFeedback);
			
			logger.info("Calling Kafka Producer Service: {}", feedbackJson);
			producerService.sendMessage("feedback-topic", feedbackJson);
		} catch (JsonProcessingException e) {
			logger.error("Exception while Call Kafka Service: {}", e);
		}
		
		return addFeedback;
	}

	@Override
	public Feedback featchFeedback(String feedbackId) {
		
		logger.info("Finding Feedback by its id: {}", feedbackId);
		Feedback feedback = feedbackRepo.findById(UUID.fromString(feedbackId)).orElse(null);
		if (feedback != null) {
	        try {
	        	if(feedback.getKeyword() != null) {
	        		logger.info("Converting JSON format: {}", feedback);
		            Map<String, String> keywords = objectMapper.readValue(
		                feedback.getKeyword(), new TypeReference<Map<String, String>>() {});
		            feedback.setKeywords(keywords);
	        	}
	        } catch (IOException e) {
	           logger.info("Exception while convert json object: {}", e);
	        }
	    }
		return feedback;
	}
	
}








