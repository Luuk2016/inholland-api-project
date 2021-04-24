package nl.inholland.inf2a.groep4.inhollandapiproject.service;

import nl.inholland.inf2a.groep4.inhollandapiproject.model.User;
import nl.inholland.inf2a.groep4.inhollandapiproject.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository)
    {
        this.userRepository = userRepository;
    }

    // Get all users
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Add a new user
    public User addUser(User user) {
        return userRepository.save(user);
    }
}
