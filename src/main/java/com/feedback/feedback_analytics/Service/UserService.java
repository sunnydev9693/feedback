package com.feedback.feedback_analytics.Service;


import com.feedback.feedback_analytics.model.User;

public interface UserService {

	User findUser(String userName);

	User addNewUser(User user);

}
