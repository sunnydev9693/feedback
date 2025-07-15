package com.feedback.feedback_analytics.kafka;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class KafkaProducerService {
	
	private final KafkaTemplate<String, String> kafkaTemplate;
	
	public void sendMessage(String topic, String message) {
        kafkaTemplate.send(topic, message);
    }
	
}
