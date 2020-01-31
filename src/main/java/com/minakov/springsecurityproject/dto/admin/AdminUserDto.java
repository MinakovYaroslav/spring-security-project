package com.minakov.springsecurityproject.dto.admin;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.minakov.springsecurityproject.dto.AbstractDto;
import com.minakov.springsecurityproject.model.User;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Yaroslav Minakov
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class AdminUserDto extends AbstractDto {

    private String username;
    private String firstName;
    private String lastName;
    private String specialty;
    private String phoneNumber;
    private List<AdminRoleDto> roles;
    private List<AdminSkillDto> skills;
    private Boolean phoneVerified;
    private Boolean status;

    @Builder
    public AdminUserDto(Long id,
                        String username,
                        String firstName,
                        String lastName,
                        String specialty,
                        String phoneNumber,
                        List<AdminRoleDto> roles,
                        List<AdminSkillDto> skills,
                        Boolean phoneVerified,
                        Boolean status) {
        super(id);
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.specialty = specialty;
        this.phoneNumber = phoneNumber;
        this.roles = roles;
        this.skills = skills;
        this.phoneVerified = phoneVerified;
        this.status = status;
    }

    public User toEntity(User user) {
        user.setUsername(username);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setSpecialty(specialty);
        user.setPhoneNumber(phoneNumber);
        user.setPhoneVerified(phoneVerified);
        user.setRoles(roles.stream()
                .map(AdminRoleDto::toEntity)
                .collect(Collectors.toList()));
        user.setSkills(skills.stream()
                .map(AdminSkillDto::toEntity)
                .collect(Collectors.toList()));
        user.setStatus(status);

        return user;
    }

    public User toEntity() {
        return User.builder()
                .id(id)
                .username(username)
                .firstName(firstName)
                .lastName(lastName)
                .specialty(specialty)
                .phoneNumber(phoneNumber)
                .phoneVerified(phoneVerified)
                .roles(roles.stream()
                        .map(AdminRoleDto::toEntity)
                        .collect(Collectors.toList()))
                .skills(skills.stream()
                        .map(AdminSkillDto::toEntity)
                        .collect(Collectors.toList()))
                .status(status)
                .build();
    }

    public static AdminUserDto toDto(User user) {
        return AdminUserDto.builder()
                .id(user.getId())
                .username(user.getUsername())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .specialty(user.getSpecialty())
                .phoneNumber(user.getPhoneNumber())
                .phoneVerified(user.getPhoneVerified())
                .roles(user.getRoles().stream()
                        .map(AdminRoleDto::toDto)
                        .collect(Collectors.toList()))
                .skills(user.getSkills().stream()
                        .map(AdminSkillDto::toDto)
                        .collect(Collectors.toList()))
                .status(user.getStatus())
                .build();
    }
}
