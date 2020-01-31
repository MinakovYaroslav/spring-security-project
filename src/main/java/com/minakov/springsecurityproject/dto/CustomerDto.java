package com.minakov.springsecurityproject.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.minakov.springsecurityproject.model.Customer;
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
public class CustomerDto extends AbstractDto {

    private String name;
    private List<ProjectDto> projects;

    @Builder
    public CustomerDto(Long id,
                       String name,
                       List<ProjectDto> projects) {
        super(id);
        this.name = name;
        this.projects = projects;
    }

    public static CustomerDto toDto(Customer customer) {
        return CustomerDto.builder()
                .id(customer.getId())
                .name(customer.getName())
                .projects(customer.getProjects().stream()
                        .map(ProjectDto::toDto)
                        .collect(Collectors.toList()))
                .build();
    }
}