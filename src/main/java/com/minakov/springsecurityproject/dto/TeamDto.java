package com.minakov.springsecurityproject.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.minakov.springsecurityproject.model.Team;
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
public class TeamDto extends AbstractDto {

    private String name;
    private List<Long> usersId;

    @Builder
    public TeamDto(Long id, String name, List<Long> usersId) {
        super(id);
        this.name = name;
        this.usersId = usersId;
    }

    public Team toEntity() {
        return Team.builder()
                .name(name)
                .users(usersId.stream()
                        .map(userId -> User.builder()
                                .id(userId)
                                .build())
                        .collect(Collectors.toList()))
                .build();
    }

    public Team toEntity(Team team) {
        team.setName(name);
        team.setUsers(usersId.stream()
                .map(userId -> User.builder()
                        .id(userId)
                        .build())
                .collect(Collectors.toList()));
        return team;
    }

    public static TeamDto toDto(Team team) {
        return TeamDto.builder()
                .id(team.getId())
                .name(team.getName())
                .usersId(team.getUsers().stream()
                        .map(User::getId)
                        .collect(Collectors.toList()))
                .build();
    }
}