package io.inholland.groep4.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.inholland.groep4.api.model.Transaction;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class TransactionsApiControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser(username = "john", password = "test", roles = "EMPLOYEE")
    public void getTransactionsShouldReturnOk() throws Exception {
        this.mockMvc.perform(get("/transactions"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "john", password = "test", roles = "EMPLOYEE")
    public void getSpecificTransactionShouldReturnOk() throws Exception {
        this.mockMvc.perform(get("/transactions/9"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "john", password = "test", roles = "EMPLOYEE")
    public void createTransactionShouldReturnOk() throws Exception {
        // Create a new transaction
        Transaction transaction = new Transaction();
        transaction.setSender("yeet");
        transaction.setReceiver("woi");
        transaction.setAmount(49.95);
        transaction.setDescription("Omega");

        this.mockMvc.perform(MockMvcRequestBuilders
                        .post("/transactions")
                        .content(asJsonString(transaction))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
