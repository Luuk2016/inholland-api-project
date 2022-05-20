package io.inholland.groep4.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.inholland.groep4.api.model.User;
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
public class UsersApiControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser(username = "john", password = "test", roles = "EMPLOYEE")
    public void getUserShouldReturnOk() throws Exception {
        this.mockMvc.perform(get("/users"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "john", password = "test", roles = "EMPLOYEE")
    public void getSpecificAccountShouldReturnOk() throws Exception {
        this.mockMvc.perform(get("/users/2"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "john", password = "test", roles = "EMPLOYEE")
    public void createUserShouldReturnOk() throws Exception {
        User user = new User();
        user.setUsername("peter");
        user.setPassword("test");
        user.setFirstName("Peter");
        user.setLastName("Griffin");
        user.setEmail("peter@example.com");
        user.setBirthdate("01/01/1970");

        this.mockMvc.perform(MockMvcRequestBuilders
                        .post("/users")
                        .content(asJsonString(user))
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
