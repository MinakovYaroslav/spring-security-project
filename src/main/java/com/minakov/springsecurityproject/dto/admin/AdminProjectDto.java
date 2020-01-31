package com.minakov.springsecurityproject.dto.admin;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.minakov.springsecurityproject.dto.AbstractDto;
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
public class AdminProjectDto extends AbstractDto {

    private String name;
    private BigDecimal budget;
    private List<AdminTeamDto> teams;
    private Boolean status;

    @Builder
    public AdminProjectDto(Long id,
                           String name,
                           BigDecimal budget,
                           List<AdminTeamDto> teams,
                           Boolean status) {
        super(id);
        this.name = name;
        this.budget = budget;
        this.teams = teams;
        this.status = status;
    }

    public Project toEntity() {
        return Project.builder()
                .id(id)
                .name(name)
                .budget(budget)
                .teams(teams.stream()
                        .map(AdminTeamDto::toEntity)
                        .collect(Collectors.toList()))
                .status(status)
                .build();
    }

    public static AdminProjectDto toDto(Project project) {
        return AdminProjectDto.builder()
                .id(project.getId())
                .name(project.getName())
                .budget(project.getBudget())
                .teams(project.getTeams().stream()
                        .map(AdminTeamDto::toDto)
                        .collect(Collectors.toList()))
                .status(project.getStatus())
                .build();
    }
}