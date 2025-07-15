package com.feedback.feedback_analytics.serviceimpl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.feedback.feedback_analytics.ExceptionHandler.NotFoundException;
import com.feedback.feedback_analytics.Service.UserService;
import com.feedback.feedback_analytics.model.User;
import com.feedback.feedback_analytics.repository.UserRepo;
import com.feedback.feedback_analytics.util.Roles;

@SpringBootTest
public class UserServiceImplTest {
	
	@Autowired private UserService userService;
	@MockBean private UserRepo userRepo;
	
	@Test
	@Order(1)
	@Tag("important")//to run single test cases through maven ("mvn test -Dgroups=important")
    void findUser_shouldReturnUser_whenUserExists() {
		
        User user = User.builder()
        				.userId(UUID.randomUUID())
        				.userName("test")
        				.email("test@gmail.com")
        				.password("test")
        				.role(Roles.USER).build();
        
        when(userRepo.findByUserName("test")).thenReturn(Optional.of(user));

        User result = userService.findUser("test");

        assertThat(result.getUserName()).isEqualTo("test");
    }

    @Test
    void findUser_shouldThrowNotFoundException_whenUserDoesNotExist() {
        when(userRepo.findByUserName("invalid")).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> userService.findUser("invalid"));
    }

    @Test
    @Order(2)
    void addNewUser_shouldSaveUser_whenNotExists() {
    	User newUser = User.builder()
						.userId(UUID.randomUUID())
						.userName("test")
						.email("test@gmail.com")
						.password("test")
						.role(Roles.USER).build();
        when(userRepo.findByUserName("test")).thenReturn(Optional.empty());
        when(userRepo.save(any(User.class))).thenAnswer(i -> i.getArgument(0));

        User savedUser = userService.addNewUser(newUser);

        assertThat(savedUser.getUserId()).isNotNull();
        assertThat(savedUser.getUserName()).isEqualTo("test");
        verify(userRepo).save(any(User.class));
    }

    @Test
    @Order(3)
    void addNewUser_shouldThrowNotFoundException_whenAlreadyExists() {
        User existingUser = User.builder()
								.userId(UUID.randomUUID())
								.userName("test")
								.email("test@gmail.com")
								.password("test")
								.role(Roles.USER).build();
        when(userRepo.findByUserName("test")).thenReturn(Optional.of(existingUser));

        assertThrows(NotFoundException.class, () -> userService.addNewUser(existingUser));
    }
    
}
