package io.inholland.groep4.api.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class TransactionsApiControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser(username = "john",password = "test", roles = "EMPLOYEE")
    public void getTransactionsShouldReturnOk() throws Exception {
        this.mockMvc.perform(get("/transactions"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "john",password = "test", roles = "EMPLOYEE")
    public void getSpecificTransactionShouldReturnOk() throws Exception {
        this.mockMvc.perform(get("/transactions/6"))
                .andExpect(status().isOk());
    }
}
