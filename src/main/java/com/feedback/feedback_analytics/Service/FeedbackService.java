package com.feedback.feedback_analytics.Service;

import com.feedback.feedback_analytics.model.Feedback;
import com.feedback.feedback_analytics.util.FeedbackDTO;

public interface FeedbackService {

	Feedback addFeedback(FeedbackDTO feedback);

	Feedback featchFeedback(String feedbackId);

}
