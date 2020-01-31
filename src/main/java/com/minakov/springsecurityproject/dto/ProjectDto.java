package com.minakov.springsecurityproject.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.minakov.springsecurityproject.model.Project;
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
    private List<TeamDto> teams;

    @Builder
    public ProjectDto(Long id,
                      String name,
                      BigDecimal budget,
                      List<TeamDto> teams) {
        super(id);
        this.name = name;
        this.budget = budget;
        this.teams = teams;
    }

    public static ProjectDto toDto(Project project) {
        return ProjectDto.builder()
                .id(project.getId())
                .name(project.getName())
                .budget(project.getBudget())
                .teams(project.getTeams().stream()
                        .map(TeamDto::toDto)
                        .collect(Collectors.toList()))
                .build();
    }
}