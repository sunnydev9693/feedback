package com.feedback.feedback_analytics.integration;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.feedback.feedback_analytics.repository.UserRepo;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class UserIntegrationTest {

    @Autowired private MockMvc mockMvc;

    @Autowired private UserRepo userRepo;

    @BeforeEach
    void setUp() {
        userRepo.deleteAll();
    }

    @Test
    void createUserAndGetUser_shouldWork() throws Exception {
    	// Create user
        mockMvc.perform(post("/api/v1/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"userName\":\"test\", \"email\":\"test@gmail.com\", \"password\":\"test\", \"role\":\"USER\"}"))
        		.andDo(print())//for debug 
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.data.userName").value("test"));

        // Fetch created user
        mockMvc.perform(get("/api/v1/user/test"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.userName").value("test"));
    }
}







