package io.inholland.groep4.api.controller;

import io.inholland.groep4.api.AccountsApi;
import io.inholland.groep4.api.model.DTO.UserAccountDTO;
import io.inholland.groep4.api.model.User;
import io.inholland.groep4.api.model.UserAccount;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.inholland.groep4.api.service.UserAccountService;
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
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-05-31T22:24:07.069Z[GMT]")
@RestController
public class AccountsApiController implements AccountsApi {

    private static final Logger log = LoggerFactory.getLogger(AccountsApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @Autowired
    private UserAccountService userAccountService;

    @Autowired
    private UserService userService;

    @Autowired
    public AccountsApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    @PreAuthorize("hasAnyRole('EMPLOYEE','USER')")
    public ResponseEntity<?> getAccounts() {
        try {
            // Create a empty list for accounts
            List<UserAccount> accounts = new ArrayList<>();

            // Check the role of the user
            if (request.isUserInRole("ROLE_EMPLOYEE")) {
                // User is an employee, getting all accounts
                accounts = userAccountService.getAllAccounts();
            } else {
                // Get the security information
                Principal principal = request.getUserPrincipal();

                // Get the current user
                User user = userService.findByUsername(principal.getName());

                // Get the user accounts
                accounts = userAccountService.getAccountsByUser(user);
            }
            return ResponseEntity.status(HttpStatus.OK).body(accounts);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PreAuthorize("hasAnyRole('EMPLOYEE','USER')")
    public ResponseEntity<?> getSpecificAccount(@Parameter(in = ParameterIn.PATH, description = "The account ID", required = true, schema = @Schema()) @PathVariable("id") Long id) {
        try {
            Principal principal = request.getUserPrincipal();
            User user = userService.findByUsername(principal.getName());

            // Check if the user is an employee or account owner
            if (request.isUserInRole("ROLE_EMPLOYEE") || userAccountService.checkIfAccountBelongsToOwner(user, id)) {
                UserAccount account = userAccountService.getAccountById(id);

                return ResponseEntity.status(HttpStatus.OK).body(account);
            } else {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PreAuthorize("hasRole('EMPLOYEE')")
    public ResponseEntity<?> postAccount(@Parameter(in = ParameterIn.DEFAULT, description = "", schema = @Schema()) @Valid @RequestBody UserAccountDTO body) {
        try {
            // Create a new account
            UserAccount userAccount = new UserAccount();

            // Set the properties
            userAccount.setAccountType(body.getAccountType());
            userAccount.setOwner(body.getOwner());
            userAccount.setLowerLimit(body.getLowerLimit());
            userAccount.setAccountStatus(body.getAccountStatus());
            userAccount.setAccountBalance(0.00);

            // Store the new account
            UserAccount result = userAccountService.add(userAccount, true);

            return ResponseEntity.status(HttpStatus.OK).body(result);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PreAuthorize("hasRole('EMPLOYEE')")
    public ResponseEntity<UserAccount> updateAccount(@Parameter(in = ParameterIn.PATH, description = "The account ID", required = true, schema = @Schema()) @PathVariable("id") Long id, @Parameter(in = ParameterIn.DEFAULT, description = "", schema = @Schema()) @Valid @RequestBody UserAccount body) {
        try {
            body.setId(id);
            UserAccount result = userAccountService.save(body);
            return ResponseEntity.status(200).body(result);
        } catch (IllegalArgumentException iae) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}
