package com.feedback.feedback_analytics.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.feedback.feedback_analytics.Service.UserService;
import com.feedback.feedback_analytics.model.User;
import com.feedback.feedback_analytics.util.ApiResponse;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {
	
	@Autowired UserService userService;
	
	@GetMapping("/{userName}")
	public ResponseEntity<ApiResponse<User>> getUser(@PathVariable("userName") String userName){
		return new ResponseEntity<>(new ApiResponse<>
		(userService.findUser(userName), true, "User fetched successfully"),HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<ApiResponse<User>> addUser(@RequestBody User user){
		return new ResponseEntity<>(new ApiResponse<>
		(userService.addNewUser(user),true,"User Added Successfully"),HttpStatus.CREATED);
	}
	
}




