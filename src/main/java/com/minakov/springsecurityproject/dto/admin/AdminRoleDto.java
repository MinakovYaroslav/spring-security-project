package com.minakov.springsecurityproject.dto.admin;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.minakov.springsecurityproject.dto.AbstractDto;
import com.minakov.springsecurityproject.model.Role;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author Yaroslav Minakov
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class AdminRoleDto extends AbstractDto {

    private String name;
    private Boolean status;

    @Builder
    public AdminRoleDto(Long id,
                        String name,
                        Boolean status) {
        super(id);
        this.name = name;
        this.status = status;
    }

    public Role toEntity() {
        return Role.builder()
                .id(id)
                .name(name)
                .status(status)
                .build();
    }

    public static AdminRoleDto toDto(Role role) {
        return AdminRoleDto.builder()
                .id(role.getId())
                .name(role.getName())
                .status(role.getStatus())
                .build();
    }
}
