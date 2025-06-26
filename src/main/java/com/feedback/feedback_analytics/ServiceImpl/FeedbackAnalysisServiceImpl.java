package com.feedback.feedback_analytics.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.feedback.feedback_analytics.Service.FeedbackAnalysisService;
import com.feedback.feedback_analytics.repository.FeedbackAnalysisRepo;

@Service
public class FeedbackAnalysisServiceImpl implements FeedbackAnalysisService {
	
	@Autowired FeedbackAnalysisRepo feedbackAbAnalysisRepo;
	
	
}
