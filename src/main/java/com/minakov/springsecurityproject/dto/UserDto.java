package com.minakov.springsecurityproject.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.minakov.springsecurityproject.model.User;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Yaroslav Minakov
 */
@EqualsAndHashCode(callSuper = true)
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDto extends AbstractDto {

    private String username;
    private String firstName;
    private String lastName;
    private String specialty;
    private List<RoleDto> roles;
    private List<SkillDto> skills;

    @Builder
    public UserDto(Long id,
                   String username,
                   String firstName,
                   String lastName,
                   String specialty,
                   List<RoleDto> roles,
                   List<SkillDto> skills) {
        super(id);
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.specialty = specialty;
        this.roles = roles;
        this.skills = skills;
    }

    public static UserDto toDto(User user) {
        return UserDto.builder()
                .id(user.getId())
                .username(user.getUsername())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .specialty(user.getSpecialty())
                .roles(user.getRoles().stream()
                        .map(RoleDto::toDto)
                        .collect(Collectors.toList()))
                .skills(user.getSkills().stream()
                        .map(SkillDto::toDto)
                        .collect(Collectors.toList()))
                .build();
    }
}
