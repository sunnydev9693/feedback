package com.feedback.feedback_analytics.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.feedback.feedback_analytics.kafka.KafkaProducerService;
import com.feedback.feedback_analytics.util.ApiResponse;

@RestController
@RequestMapping("api/v1/kafka")
public class KafkaController {
	
	@Autowired KafkaProducerService kafkaProducer;
	
	@PostMapping("add/message")
	public ResponseEntity<ApiResponse<String>> addMessage(@RequestBody Map<String,String> message){
		ObjectMapper objectMapper = new ObjectMapper();
		String feedbackJson = null;
        try {
			feedbackJson = objectMapper.writeValueAsString(message);
			kafkaProducer.sendMessage("test-topic", feedbackJson);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(new ApiResponse<>(feedbackJson,true,"Producer Added Successfully"), HttpStatus.OK);
	}
	
}








