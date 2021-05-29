package io.inholland.groep4.configuration;

import io.inholland.groep4.api.model.Role;
import io.inholland.groep4.api.model.User;
import io.inholland.groep4.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class MyApplicationRunner implements ApplicationRunner {

    @Autowired
    UserService userService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        User user = new User();
        user.setUsername("john");
        user.setPassword("test");
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setEmail("johndoe@example.com");
        user.setBirthdate("01/01/1970");
        user.setRoles(Arrays.asList(Role.ROLE_USER, Role.ROLE_ADMIN));
        userService.add(user);
    }
}
