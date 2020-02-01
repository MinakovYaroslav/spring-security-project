package com.minakov.springsecurityproject.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.minakov.springsecurityproject.model.Customer;
import com.minakov.springsecurityproject.model.Project;
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
    private List<Long> projectsId;

    @Builder
    public CustomerDto(Long id,
                       String name,
                       List<Long> projectsId) {
        super(id);
        this.name = name;
        this.projectsId = projectsId;
    }

    public Customer toEntity() {
        return Customer.builder()
                .name(name)
                .projects(projectsId.stream()
                        .map(projectId -> Project.builder()
                                .id(projectId).build())
                        .collect(Collectors.toList()))
                .build();
    }

    public Customer toEntity(Customer customer) {
        customer.setName(name);
        customer.setProjects(projectsId.stream()
                .map(projectId -> Project.builder()
                        .id(projectId)
                        .build())
                .collect(Collectors.toList()));

        return customer;
    }

    public static CustomerDto toDto(Customer customer) {
        return CustomerDto.builder()
                .id(customer.getId())
                .name(customer.getName())
                .projectsId(customer.getProjects().stream()
                        .map(Project::getId)
                        .collect(Collectors.toList()))
                .build();
    }
}