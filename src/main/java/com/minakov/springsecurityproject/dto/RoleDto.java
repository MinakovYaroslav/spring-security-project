package com.minakov.springsecurityproject.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.minakov.springsecurityproject.model.Role;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Yaroslav Minakov
 */

@EqualsAndHashCode(callSuper = true)
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class RoleDto extends AbstractDto {

    private String name;

    @Builder
    public RoleDto(Long id, String name) {
        super(id);
        this.name = name;
    }

    public Role toEntity(Role role) {
        return Role.builder()
                .id(id)
                .name(name)
                .build();
    }

    public static RoleDto toDto(Role role) {
        return RoleDto.builder()
                .id(role.getId())
                .name(role.getName())
                .build();
    }
}