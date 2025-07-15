package com.feedback.feedback_analytics.model;

import java.util.List;
import java.util.UUID;

import org.hibernate.annotations.Fetch;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.feedback.feedback_analytics.util.Roles;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
public class User {
	
	@Id
	private UUID userId;
	
	@Column(nullable = false, unique = true, length = 50)
	private String  userName;
	
	@Column(nullable = false, unique = true, length = 50)
	private String email;
	
	@Column(nullable =false, length = 50)
	private String password;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false, length = 15)
	private Roles role;
	
	@JsonManagedReference
	@OneToMany(fetch = FetchType.LAZY ,mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Feedback> feedback;
	
}










