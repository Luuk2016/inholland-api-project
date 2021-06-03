package io.inholland.groep4.api.controller;

import io.inholland.groep4.api.UsersApi;
import io.inholland.groep4.api.model.DTO.UserDTO;
import io.inholland.groep4.api.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.inholland.groep4.api.service.UserService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-05-31T22:24:07.069Z[GMT]")
@RestController
public class UsersApiController implements UsersApi {

    private static final Logger log = LoggerFactory.getLogger(UsersApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @Autowired
    private UserService userService;

    @Autowired
    public UsersApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    @PreAuthorize("hasRole('EMPLOYEE')")
    public ResponseEntity<List<User>> getUsers() {
        // @TODO Show error message if no users found
        List<User> users = userService.getAllUsers();
        return ResponseEntity.status(200).body(users);
    }

    @PreAuthorize("hasRole('EMPLOYEE')")
    public ResponseEntity<User> getSpecificUser(@Parameter(in = ParameterIn.PATH, description = "The user ID", required=true, schema=@Schema()) @PathVariable("id") Long id) {
        // @TODO Show error message if no user found
        try {
            User user = userService.getSpecificAccount(id);
            return ResponseEntity.status(200).body(user);
        } catch (IllegalArgumentException iae) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PreAuthorize("hasRole('EMPLOYEE')")
    public ResponseEntity<User> postUser(@Parameter(in = ParameterIn.DEFAULT, description = "", schema=@Schema()) @Valid @RequestBody UserDTO body) {
        try {
            User user = new User();
            user.setUsername(body.getUsername());
            user.setPassword(body.getPassword());
            user.setFirstName(body.getFirstName());
            user.setLastName(body.getLastName());
            user.setEmail(body.getEmail());
            user.setBirthdate(body.getBirthdate());

            User result = userService.add(user, false);
            return ResponseEntity.status(200).body(result);
        } catch (IllegalArgumentException iae) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PreAuthorize("hasRole('EMPLOYEE')")
    public ResponseEntity<User> updateUser(@Parameter(in = ParameterIn.PATH, description = "The user ID", required=true, schema=@Schema()) @PathVariable("id") Integer id, @Parameter(in = ParameterIn.DEFAULT, description = "", schema=@Schema()) @Valid @RequestBody User body) {
        // @TODO: Update specific user
        return null;
    }
}
