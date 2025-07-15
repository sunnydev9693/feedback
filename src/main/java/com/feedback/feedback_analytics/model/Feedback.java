package com.feedback.feedback_analytics.model;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.feedback.feedback_analytics.util.Sentiment;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.Data;

@Entity
@Table(name ="feedback")
@Data
public class Feedback {
	
	@Id
	private UUID feedbackId;
	
	@Column(nullable = false, length = 150)
	private String department;
	
	@Column(length = 50)
	private String feedbackType;
	
	@Column(nullable = false, columnDefinition = "Text")
	private String message;
	
	@Enumerated(EnumType.STRING)
	@Column(length = 15)
	private Sentiment sentiment;
	
	@JsonIgnore
	@Column(columnDefinition = "TEXT")
	private String keyword;
	
	@CreationTimestamp
	@Column(updatable = false)
	private LocalDateTime createdAt;
	
	@ManyToOne
	@JsonBackReference
	@JoinColumn(name = "user_id", nullable = false)
	private User user;
	
	@Transient
	Map<String,String> keywords;

}






