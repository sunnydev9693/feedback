package com.feedback.feedback_analytics.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.feedback.feedback_analytics.model.User;

@Repository
public interface UserRepo extends JpaRepository<User, UUID>{

	Optional<User> findByUserName(String userName);

}
