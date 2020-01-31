package com.minakov.springsecurityproject.model;

import lombok.Data;

/**
 * @author Yaroslav Minakov
 */
@Data
public class UsernamePasswordAuthenticationRequest {

    private String username;
    private String password;
}
