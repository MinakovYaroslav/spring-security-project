package com.minakov.springsecurityproject.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
public class TeamDto extends AbstractDto {

    private String name;
    private List<UserDto> users;

    @Builder
    public TeamDto(Long id, String name, List<UserDto> users) {
        super(id);
        this.name = name;
        this.users = users;
    }

    public static TeamDto toDto(Team team) {
        return TeamDto.builder()
                .id(team.getId())
                .name(team.getName())
                .users(team.getUsers().stream()
                        .map(UserDto::toDto)
                        .collect(Collectors.toList()))
                .build();
    }
}