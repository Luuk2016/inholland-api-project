package io.inholland.groep4.api.model;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class UserTest {

    @Test
    public void createUserShouldNotBeNull() {
        User createdUser = new User();
        createdUser.setId(15L);
        createdUser.setFirstName("John");
        createdUser.setLastName("Doe");
        createdUser.setEmail("johndoe@example.com");
        createdUser.setUsername("test-employee1");
        createdUser.setPassword("password");
        createdUser.setBirthdate("01/01/1970");
        createdUser.setStatus(Collections.singletonList(User.StatusEnum.ACTIVE));
        createdUser.setRoles(Arrays.asList(Role.ROLE_EMPLOYEE, Role.ROLE_USER));

        assertNotNull(createdUser);
    }
}
