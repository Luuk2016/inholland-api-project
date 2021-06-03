package io.inholland.groep4.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.inholland.groep4.api.TransactionsApi;
import io.inholland.groep4.api.model.DTO.TransactionDTO;
import io.inholland.groep4.api.model.Transaction;
import io.inholland.groep4.api.service.TransactionService;
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
public class TransactionsApiController implements TransactionsApi {

    private static final Logger log = LoggerFactory.getLogger(TransactionsApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @Autowired
    private TransactionService transactionService;

    @Autowired
    public TransactionsApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    @PreAuthorize("hasRole('EMPLOYEE')")
    public ResponseEntity<List<Transaction>> getTransactions() {
        // @TODO Get all transactions if the user is an employee, otherwise only the user created transactions
        List<Transaction> transactions = transactionService.getAllTransactions();
        return ResponseEntity.status(200).body(transactions);
    }

    public ResponseEntity<Transaction> getSpecificTransaction(@Parameter(in = ParameterIn.PATH, description = "The transaction ID", required=true, schema=@Schema()) @PathVariable("id") Long id) {
        // @TODO Only allow getting transactions if owned, or if employee, get all
        try {
            Transaction transaction = transactionService.getTransactionById(id);
            return ResponseEntity.status(200).body(transaction);
        } catch (IllegalArgumentException iae) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    public ResponseEntity<Transaction> postTransactions(@Parameter(in = ParameterIn.DEFAULT, description = "", schema=@Schema()) @Valid @RequestBody TransactionDTO body) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<Transaction>(objectMapper.readValue("{\n  \"dateTime\" : \"2000-01-23T04:56:07.000+00:00\",\n  \"amount\" : 12345.67,\n  \"sender\" : \"NL91 ABNA 0417 1643 00\",\n  \"reciever\" : \"NL91 ABNA 0417 1643 00\",\n  \"description\" : \"Rolex\",\n  \"id\" : 99999\n}", Transaction.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<Transaction>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<Transaction>(HttpStatus.NOT_IMPLEMENTED);
    }
}
