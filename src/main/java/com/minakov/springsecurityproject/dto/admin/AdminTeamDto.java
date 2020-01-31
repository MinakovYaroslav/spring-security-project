package com.minakov.springsecurityproject.dto.admin;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.minakov.springsecurityproject.dto.AbstractDto;
import com.minakov.springsecurityproject.model.Team;
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
public class AdminTeamDto extends AbstractDto {

    private String name;
    private List<AdminUserDto> users;
    private Boolean status;

    @Builder
    public AdminTeamDto(Long id,
                        String name,
                        List<AdminUserDto> users,
                        Boolean status) {
        super(id);
        this.name = name;
        this.users = users;
        this.status = status;
    }

    public Team toEntity() {
        return Team.builder()
                .id(id)
                .name(name)
                .users(users.stream()
                        .map(AdminUserDto::toEntity)
                        .collect(Collectors.toList()))
                .status(status)
                .build();
    }

    public static AdminTeamDto toDto(Team team) {
        return AdminTeamDto.builder()
                .id(team.getId())
                .name(team.getName())
                .users(team.getUsers().stream()
                        .map(AdminUserDto::toDto)
                        .collect(Collectors.toList()))
                .status(team.getStatus())
                .build();
    }
}
