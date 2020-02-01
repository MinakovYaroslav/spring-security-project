package com.minakov.springsecurityproject.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.minakov.springsecurityproject.model.Role;
import com.minakov.springsecurityproject.model.Skill;
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
    private String phoneNumber;
    private List<RoleDto> roles;
    private List<SkillDto> skills;

    @Builder
    public UserDto(Long id,
                   String username,
                   String firstName,
                   String lastName,
                   String specialty,
                   String phoneNumber,
                   List<RoleDto> roles,
                   List<SkillDto> skills) {
        super(id);
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.specialty = specialty;
        this.phoneNumber = phoneNumber;
        this.roles = roles;
        this.skills = skills;
    }

    public User toEntity(User user) {
        user.setUsername(username);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setSpecialty(specialty);
        user.setPhoneNumber(phoneNumber);
        user.setRoles(roles.stream()
                .map(roleDto -> Role.builder()
                        .id(roleDto.getId())
                        .build())
                .collect(Collectors.toList()));
        user.setSkills(skills.stream()
                .map(skillDto -> Skill.builder()
                        .id(skillDto.getId())
                        .build())
                .collect(Collectors.toList()));

        return user;
    }

    public static UserDto toDto(User user) {
        return UserDto.builder()
                .id(user.getId())
                .username(user.getUsername())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .specialty(user.getSpecialty())
                .phoneNumber(user.getPhoneNumber())
                .roles(user.getRoles().stream()
                        .map(RoleDto::toDto)
                        .collect(Collectors.toList()))
                .skills(user.getSkills().stream()
                        .map(SkillDto::toDto)
                        .collect(Collectors.toList()))
                .build();
    }
}
