package com.minakov.springsecurityproject.security;

import com.minakov.springsecurityproject.model.Role;
import com.minakov.springsecurityproject.model.User;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Yaroslav Minakov
 */
public final class UserPrincipalFactory {

    public static UserPrincipal create(User user) {
        return UserPrincipal.builder()
                .id(user.getId())
                .username(user.getUsername())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .specialty(user.getSpecialty())
                .password(user.getPassword())
                .status(user.getStatus())
                .phoneVerified(user.getPhoneVerified())
                .authorities(mapToGrantedAuthorities(user.getRoles()))
                .build();
    }

    private static List<SimpleGrantedAuthority> mapToGrantedAuthorities(List<Role> userRoles) {
        return userRoles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
    }
}