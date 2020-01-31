package com.minakov.springsecurityproject.rest;

import com.minakov.springsecurityproject.model.Role;
import com.minakov.springsecurityproject.security.jwt.JwtTokenProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Collections;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Yaroslav Minakov
 */
@SpringBootTest
@AutoConfigureMockMvc
class AdminRestControllerV1Test {

    private final String ADMIN_ENDPOINT = "/api/v1/admin/";
    private String token;

    @Value("${jwt.token.header}")
    private String tokenHeader;

    @Value("${jwt.token.prefix}")
    private String tokenPrefix;

    @Autowired
    private MockMvc mvc;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @BeforeEach
    void setUp() {
        Role role = new Role();
        String ROLE = "ROLE_ADMIN";
        role.setName(ROLE);
        String USERNAME = "DonnaMejia";
        token = jwtTokenProvider.createToken(USERNAME, Collections.singletonList(role));
    }

    @Test
    void getUsers() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get(ADMIN_ENDPOINT + "users").header(tokenHeader, tokenPrefix + token)).andExpect(status().isOk());
    }

    @Test
    void getUser() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get(ADMIN_ENDPOINT + "users/1").header(tokenHeader, tokenPrefix + token)).andExpect(status().isOk());
    }

    @Test
    void updateUser() {
    }

    @Test
    void getRoles() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get(ADMIN_ENDPOINT + "roles").header(tokenHeader, tokenPrefix + token)).andExpect(status().isOk());
    }
}