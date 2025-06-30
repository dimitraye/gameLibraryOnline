package com.gameLibraryOnline.rest.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gameLibraryOnline.rest.dto.RegisterRequest;
import com.gameLibraryOnline.rest.repository.IUserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class AuthControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private IUserRepository userRepository;

    @Test
    void shouldRegisterUserSuccessfully() throws Exception {
        RegisterRequest request = new RegisterRequest();
        request.setUsername("newuser3");
        request.setPassword("newpass123");
        request.setFirstname("Miss");
        request.setLastname("Robinson");
        request.setEmail("newuser@example.com");
        request.setBirthDate("2000-01-01");
        request.setGender(true);
        request.setAdress("1 rue test");
        request.setPostCode("75000");
        request.setCity("Paris");
        request.setCountry("France");
        request.setPhoneNumber("0600000000");

        mockMvc.perform(post("/api/auth/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success", is(true)))
                .andExpect(jsonPath("$.message", is("Inscription réussie")))
                .andExpect(jsonPath("$.token").isNotEmpty());
    }
}
