package nl.inholland.inf2a.groep4.inhollandapiproject.configuration;

import nl.inholland.inf2a.groep4.inhollandapiproject.model.User;
import nl.inholland.inf2a.groep4.inhollandapiproject.service.UserService;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class MyApplicationRunner implements ApplicationRunner {

    private UserService userService;

    public MyApplicationRunner(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        // Create two new users
        User user1 = new User("John Doe",25);
        User user2 = new User("Luuk Kenselaar",20);

        // Add the users
        userService.addUser(user1);
        userService.addUser(user2);
    }
}
