package io.inholland.groep4.api.controller;

import io.inholland.groep4.api.model.User;
import io.inholland.groep4.api.model.UserAccount;
import io.inholland.groep4.api.service.UserAccountService;
import io.inholland.groep4.api.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class AccountsApiControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser(username = "john",password = "test", roles = "EMPLOYEE")
    public void getAccountShouldReturnOk() throws Exception {
        this.mockMvc.perform(get("/accounts"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "john",password = "test", roles = "EMPLOYEE")
    public void getSpecificAccountShouldReturnOk() throws Exception {
        this.mockMvc.perform(get("/accounts/1"))
                .andExpect(status().isOk());
    }
}
