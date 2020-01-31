package com.minakov.springsecurityproject.dto;

import com.minakov.springsecurityproject.model.User;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author Yaroslav Minakov
 */
@Data
public class UserRegistrationDto {

    @NotNull
    @Size(min = 4, max = 50)
    private String username;

    @NotNull
    @Size(min = 2, max = 50)
    private String firstName;

    @NotNull
    @Size(min = 2, max = 50)
    private String lastName;

    @NotNull
    @Size(min = 4, max = 50)
    private String password;

    @NotNull
    @Size(min = 10, max = 10)
    private String phoneNumber;

    public User toEntity() {
        return User.builder()
                .username(username)
                .firstName(firstName)
                .lastName(lastName)
                .password(password)
                .phoneNumber(phoneNumber)
                .build();
    }
}
