package com.feedback.feedback_analytics.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.feedback.feedback_analytics.model.FeedbackAnalysis;

@Repository
public interface FeedbackAnalysisRepo extends JpaRepository<FeedbackAnalysis, UUID>{

}
