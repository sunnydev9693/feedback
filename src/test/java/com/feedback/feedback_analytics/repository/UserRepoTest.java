package com.feedback.feedback_analytics.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.feedback.feedback_analytics.model.User;
import com.feedback.feedback_analytics.util.Roles;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class UserRepoTest {

    @Autowired private UserRepo userRepo;

    @Test
    void findByUserName_shouldReturnUser() {
//        User user = User.builder()
//				.userId(UUID.randomUUID())
//				.userName("test")
//				.email("test@gmail.com")
//				.password("test")
//				.role(Roles.USER).build();
//        userRepo.save(user);
        
        Optional<User> result = userRepo.findByUserName("test");

        assertThat(result).isPresent();
        assertThat(result.get().getUserName()).isEqualTo("test");
    }
}
