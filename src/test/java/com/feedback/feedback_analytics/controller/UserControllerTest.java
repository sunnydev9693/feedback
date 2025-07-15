package com.feedback.feedback_analytics.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.feedback.feedback_analytics.Service.UserService;
import com.feedback.feedback_analytics.model.User;
import com.feedback.feedback_analytics.util.Roles;

@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired private MockMvc mockMvc;
    @Autowired private ObjectMapper objectMapper;

    @MockBean private UserService userService;

    @Test
    void getUser_shouldReturnUser() throws Exception {
        User user = User.builder()
				.userId(UUID.randomUUID())
				.userName("test")
				.email("test@gmail.com")
				.password("test")
				.role(Roles.USER).build();
        Mockito.when(userService.findUser("test")).thenReturn(user);
        
        mockMvc.perform(get("/api/v1/user/test"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.userName").value("test"));
    }

    @Test
    void createUser_shouldReturnCreatedUser() throws Exception {
        User user = User.builder()
						.userId(UUID.randomUUID())
						.userName("test")
						.email("test@gmail.com")
						.password("test")
						.role(Roles.USER).build();
        Mockito.when(userService.addNewUser(Mockito.any(User.class))).thenReturn(user);

        mockMvc.perform(post("/api/v1/user")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(user)))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.data.userName").value("test"))
            .andExpect(jsonPath("$.data.email").value("test@gmail.com"))
            .andExpect(jsonPath("$.data.password").value("test"))
            .andExpect(jsonPath("$.data.role").value("USER"))
        	.andExpect(jsonPath("$.success").value(true));
    }
}








