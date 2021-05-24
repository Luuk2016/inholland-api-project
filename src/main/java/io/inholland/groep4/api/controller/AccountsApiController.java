package io.inholland.groep4.api.controller;

import io.inholland.groep4.api.AccountsApi;
import io.inholland.groep4.api.model.UserAccount;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-05-24T18:28:14.004Z[GMT]")
@RestController
public class AccountsApiController implements AccountsApi {

    private static final Logger log = LoggerFactory.getLogger(AccountsApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public AccountsApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<UserAccount> getAccounts(@Parameter(in = ParameterIn.HEADER, description = "" ,required=true,schema=@Schema()) @RequestHeader(value="Bearer Token", required=true) String bearerToken) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<UserAccount>(objectMapper.readValue("{\n  \"accountStatus\" : \"Active\",\n  \"IBAN\" : \"NL91 ABNA 0417 1643 00\",\n  \"accountType\" : \"Current\",\n  \"lowerLimit\" : 0,\n  \"accountBalance\" : 1271.56\n}", UserAccount.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<UserAccount>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<UserAccount>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<UserAccount> getSpecificAccount(@Parameter(in = ParameterIn.PATH, description = "The user ID", required=true, schema=@Schema()) @PathVariable("id") Integer id,@Parameter(in = ParameterIn.HEADER, description = "" ,required=true,schema=@Schema()) @RequestHeader(value="Bearer Token", required=true) String bearerToken) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<UserAccount>(objectMapper.readValue("{\n  \"accountStatus\" : \"Active\",\n  \"IBAN\" : \"NL91 ABNA 0417 1643 00\",\n  \"accountType\" : \"Current\",\n  \"lowerLimit\" : 0,\n  \"accountBalance\" : 1271.56\n}", UserAccount.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<UserAccount>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<UserAccount>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<UserAccount> postAccount(@Parameter(in = ParameterIn.HEADER, description = "" ,required=true,schema=@Schema()) @RequestHeader(value="Bearer Token", required=true) String bearerToken,@Parameter(in = ParameterIn.DEFAULT, description = "", schema=@Schema()) @Valid @RequestBody UserAccount body) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<UserAccount>(objectMapper.readValue("{\n  \"accountStatus\" : \"Active\",\n  \"IBAN\" : \"NL91 ABNA 0417 1643 00\",\n  \"accountType\" : \"Current\",\n  \"lowerLimit\" : 0,\n  \"accountBalance\" : 1271.56\n}", UserAccount.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<UserAccount>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<UserAccount>(HttpStatus.NOT_IMPLEMENTED);
    }

}
