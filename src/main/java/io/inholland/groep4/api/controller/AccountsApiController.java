package io.inholland.groep4.api.controller;

import io.inholland.groep4.api.AccountsApi;
import io.inholland.groep4.api.model.UserAccount;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.inholland.groep4.api.service.UserAccountService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import org.iban4j.CountryCode;
import org.iban4j.Iban;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
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
    public AccountsApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<List<UserAccount>> getAccounts() {
        List<UserAccount> accounts = userAccountService.getAllAccounts();
        return ResponseEntity.status(200).body(accounts);
    }

    public ResponseEntity<UserAccount> getSpecificAccount(@Parameter(in = ParameterIn.PATH, description = "The user ID", required=true, schema=@Schema()) @PathVariable("id") Long id) {
        try {
            UserAccount userAccount = userAccountService.getSpecificAccount(id);
            return ResponseEntity.status(200).body(userAccount);
        } catch (IllegalArgumentException iae) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    public ResponseEntity<UserAccount> postAccount(@Parameter(in = ParameterIn.DEFAULT, description = "", schema=@Schema()) @Valid @RequestBody UserAccount body) {
        try {

            // Generate a new IBAN
            Iban generatedIBAN = new Iban.Builder().countryCode(CountryCode.NL).bankCode("INHO").buildRandom();

            // Check if not already in use
            // If in use, get a new one until it's unique
            while (userAccountService.existByIBAN(generatedIBAN.toString())) {
                generatedIBAN = new Iban.Builder().countryCode(CountryCode.NL).bankCode("INHO").buildRandom();
            }

            body.setIBAN(generatedIBAN.toString());
            body.setAccountBalance(0.00);
            body.setLowerLimit(100.00);
            body.setAccountStatus(UserAccount.AccountStatusEnum.ACTIVE);

            UserAccount userAccount = userAccountService.add(body);

            return ResponseEntity.status(HttpStatus.OK).body(userAccount);
        } catch (IllegalArgumentException iae) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}
