package com.minakov.springsecurityproject;

import com.minakov.springsecurityproject.model.Role;
import com.minakov.springsecurityproject.security.jwt.JwtTokenProvider;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Yaroslav Minakov
 */
@SpringBootTest
@AutoConfigureMockMvc
public class JwtAuthenticationTest {

    private final String HELLO_ENDPOINT = "/api/v1/hello";
    private final String ADMIN_ENDPOINT = "/api/v1/admin/users";
    private final String ROLE = "ROLE_USER";
    private final String USERNAME = "KobeSanchez";

    @Value("${jwt.token.header}")
    private String tokenHeader;

    @Value("${jwt.token.prefix}")
    private String tokenPrefix;

    @Autowired
    private MockMvc mvc;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Test
    public void shouldNotAllowAccessToUnauthenticatedUsers() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get(HELLO_ENDPOINT)).andExpect(status().isForbidden());
    }

    @Test
    public void shouldGenerateAuthToken() throws Exception {
        Role role = new Role();
        role.setName(ROLE);
        String token = jwtTokenProvider.createToken(USERNAME, Collections.singletonList(role));

        assertNotNull(token);
        mvc.perform(MockMvcRequestBuilders.get(HELLO_ENDPOINT).header(tokenHeader, tokenPrefix + token)).andExpect(status().isOk());
    }

    @Test
    public void shouldNotAllowAccessToAdminEndpoint() throws Exception {
        Role role = new Role();
        role.setName(ROLE);
        String token = jwtTokenProvider.createToken(USERNAME, Collections.singletonList(role));

        assertNotNull(token);
        mvc.perform(MockMvcRequestBuilders.get(ADMIN_ENDPOINT).header(tokenHeader, tokenPrefix + token)).andExpect(status().isForbidden());
    }
}
