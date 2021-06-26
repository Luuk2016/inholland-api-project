package io.inholland.groep4.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.inholland.groep4.api.model.DTO.LoginDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class AuthenticateControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void authenticateShouldReturnOk() throws Exception
    {
        LoginDTO loginDTO = new LoginDTO();
        loginDTO.setUsername("john");
        loginDTO.setPassword("test");

        this.mockMvc.perform( MockMvcRequestBuilders
            .post("/authenticate")
            .content(asJsonString(loginDTO))
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