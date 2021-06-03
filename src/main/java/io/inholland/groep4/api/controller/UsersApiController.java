package io.inholland.groep4.api.controller;

import io.inholland.groep4.api.UsersApi;
import io.inholland.groep4.api.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.inholland.groep4.api.model.UserAccount;
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
import java.io.IOException;
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
        List<User> users = userService.getAllUsers();
        return ResponseEntity.status(200).body(users);
    }

    public ResponseEntity<User> getSpecificUser(@Parameter(in = ParameterIn.PATH, description = "The user ID", required=true, schema=@Schema()) @PathVariable("id") Long id) {
        try {
            User user = userService.getSpecificAccount(id);
            return ResponseEntity.status(200).body(user);
        } catch (IllegalArgumentException iae) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    public ResponseEntity<User> postUser(@Parameter(in = ParameterIn.DEFAULT, description = "", schema=@Schema()) @Valid @RequestBody User body) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<User>(objectMapper.readValue("{\n  \"firstName\" : \"John\",\n  \"lastName\" : \"Doe\",\n  \"password\" : \"testPassword\",\n  \"birthdate\" : \"1969-04-20\",\n  \"accessLevel\" : \"Employee\",\n  \"userAccounts\" : [ {\n    \"owner\" : \"johndoe\",\n    \"accountStatus\" : \"Active\",\n    \"IBAN\" : \"NL91 ABNA 0417 1643 00\",\n    \"accountType\" : \"Current\",\n    \"lowerLimit\" : 0,\n    \"accountBalance\" : 1271.56\n  }, {\n    \"owner\" : \"johndoe\",\n    \"accountStatus\" : \"Active\",\n    \"IBAN\" : \"NL91 ABNA 0417 1643 00\",\n    \"accountType\" : \"Current\",\n    \"lowerLimit\" : 0,\n    \"accountBalance\" : 1271.56\n  } ],\n  \"id\" : 9999,\n  \"email\" : \"johndoe@groep4API.com\",\n  \"username\" : \"johndoe\",\n  \"status\" : \"Active\"\n}", User.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<User>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<User>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<User> updateUser(@Parameter(in = ParameterIn.PATH, description = "The user ID", required=true, schema=@Schema()) @PathVariable("id") Integer id, @Parameter(in = ParameterIn.DEFAULT, description = "", schema=@Schema()) @Valid @RequestBody User body) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<User>(objectMapper.readValue("{\n  \"firstName\" : \"John\",\n  \"lastName\" : \"Doe\",\n  \"password\" : \"testPassword\",\n  \"birthdate\" : \"1969-04-20\",\n  \"accessLevel\" : \"Employee\",\n  \"userAccounts\" : [ {\n    \"owner\" : \"johndoe\",\n    \"accountStatus\" : \"Active\",\n    \"IBAN\" : \"NL91 ABNA 0417 1643 00\",\n    \"accountType\" : \"Current\",\n    \"lowerLimit\" : 0,\n    \"accountBalance\" : 1271.56\n  }, {\n    \"owner\" : \"johndoe\",\n    \"accountStatus\" : \"Active\",\n    \"IBAN\" : \"NL91 ABNA 0417 1643 00\",\n    \"accountType\" : \"Current\",\n    \"lowerLimit\" : 0,\n    \"accountBalance\" : 1271.56\n  } ],\n  \"id\" : 9999,\n  \"email\" : \"johndoe@groep4API.com\",\n  \"username\" : \"johndoe\",\n  \"status\" : \"Active\"\n}", User.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<User>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<User>(HttpStatus.NOT_IMPLEMENTED);
    }
}
