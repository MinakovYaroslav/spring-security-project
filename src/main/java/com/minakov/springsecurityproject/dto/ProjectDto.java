package com.minakov.springsecurityproject.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.minakov.springsecurityproject.model.Project;
import com.minakov.springsecurityproject.model.Team;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Yaroslav Minakov
 */
@EqualsAndHashCode(callSuper = true)
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProjectDto extends AbstractDto {

    private String name;
    private BigDecimal budget;
    private List<Long> teamsId;

    @Builder
    public ProjectDto(Long id,
                      String name,
                      BigDecimal budget,
                      List<Long> teamsId) {
        super(id);
        this.name = name;
        this.budget = budget;
        this.teamsId = teamsId;
    }

    public Project toEntity() {
        return Project.builder()
                .name(name)
                .budget(budget)
                .teams(teamsId.stream()
                        .map(teamId -> Team.builder()
                                .id(teamId)
                                .build())
                        .collect(Collectors.toList()))
                .build();
    }

    public Project toEntity(Project project) {
        project.setName(name);
        project.setBudget(budget);
        project.setTeams(teamsId.stream()
                .map(teamId -> Team.builder()
                        .id(teamId)
                        .build())
                .collect(Collectors.toList()));

        return project;
    }

    public static ProjectDto toDto(Project project) {
        return ProjectDto.builder()
                .id(project.getId())
                .name(project.getName())
                .budget(project.getBudget())
                .teamsId(project.getTeams().stream()
                        .map(Team::getId)
                        .collect(Collectors.toList()))
                .build();
    }
}