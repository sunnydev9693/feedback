package com.feedback.feedback_analytics.model;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "feedback_analysis")
public class FeedbackAnalysis {
	
	@Id
	@GeneratedValue
	@Column(name = "analysis_id")
	private UUID feedbackAnalysisId;
	
	@Column(columnDefinition = "TEXT")
    private String aiResponse;
	
	@CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime processedAt;
	
	@OneToOne
	@JoinColumn(name = "feedback_id", nullable = false)
	private Feedback feedback;
	
}






