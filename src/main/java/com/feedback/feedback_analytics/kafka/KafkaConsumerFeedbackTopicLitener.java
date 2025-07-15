package com.feedback.feedback_analytics.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumerFeedbackTopicLitener {
	
	@KafkaListener(topics = "feedback-topic", groupId = "feedback-group")
	public void listen(String message) {
        System.out.println("Received: " + message);
    }
}
