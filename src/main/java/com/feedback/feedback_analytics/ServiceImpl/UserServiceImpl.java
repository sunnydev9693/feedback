package com.feedback.feedback_analytics.ServiceImpl;

import java.util.UUID;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.feedback.feedback_analytics.ExceptionHandler.NotFoundException;
import com.feedback.feedback_analytics.Service.UserService;
import com.feedback.feedback_analytics.model.User;
import com.feedback.feedback_analytics.repository.UserRepo;

@Service
public class UserServiceImpl implements UserService {
	
	private static final Logger logger = LogManager.getLogger(UserServiceImpl.class);
	@Autowired UserRepo userRepo;

	@Override
	public User findUser(String userName) {
		logger.info("Fetching user with username: {}", userName);
		
		User user = checkUser(userName);
		logger.debug("User fetched: {}", user);
		
		if(user == null)
			throw new NotFoundException("User Not Found.");
		return user;
	}
	
	@Override
	public User addNewUser(User user) {
		logger.info("Checking User already exist: {}", user.getEmail());
		
		User userExist = checkUser(user.getUserName());
		if(userExist != null)
			throw new NotFoundException("User already exists.");
		user.setUserId(UUID.randomUUID());
		
		logger.info("Adding user : {}", user);
		return userRepo.save(user);
	}
	
	private User checkUser(String userName) {
		return userRepo.findByUserName(userName).orElse(null);
	}
	
}




