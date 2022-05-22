package io.inholland.groep4.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.inholland.groep4.api.model.User;
import io.inholland.groep4.api.model.UserAccount;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class AccountsApiControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser(username = "test-employee1", password = "password", roles = "EMPLOYEE")
    public void getAccountShouldReturnOk() throws Exception {
        this.mockMvc.perform(get("/accounts"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "test-employee1", password = "password", roles = "EMPLOYEE")
    public void getSpecificAccountShouldReturnOk() throws Exception {
        this.mockMvc.perform(get("/accounts/1"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "test-employee1", password = "password", roles = "EMPLOYEE")
    public void createAccountShouldReturnOk() throws Exception {
        User user = new User();
        user.setUsername("peter");
        user.setPassword("test");
        user.setFirstName("Peter");
        user.setLastName("Griffin");
        user.setEmail("peter@example.com");
        user.setBirthdate("01/01/1970");

        UserAccount userAccount = new UserAccount();
        userAccount.setAccountType(UserAccount.AccountTypeEnum.CURRENT);
        userAccount.setOwner(user);
        userAccount.setAccountBalance(500.00);
        userAccount.setAccountStatus(UserAccount.AccountStatusEnum.ACTIVE);
        userAccount.setLowerLimit(100.00);

        this.mockMvc.perform(post("/accounts")
                        .content(asJsonString(userAccount))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
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