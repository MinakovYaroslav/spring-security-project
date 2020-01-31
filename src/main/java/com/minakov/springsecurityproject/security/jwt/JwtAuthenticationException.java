package com.minakov.springsecurityproject.security.jwt;

import org.springframework.security.core.AuthenticationException;

/**
 * @author Yaroslav Minakov
 */
public class JwtAuthenticationException extends AuthenticationException {

    public JwtAuthenticationException(String msg) {
        super(msg);
    }
}