package com.minakov.springsecurityproject.dto.admin;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.minakov.springsecurityproject.dto.AbstractDto;
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
public class AdminCustomerDto extends AbstractDto {

    private String name;
    private List<AdminProjectDto> projects;
    private Boolean status;

    @Builder
    public AdminCustomerDto(Long id,
                            String name,
                            List<AdminProjectDto> projects,
                            Boolean status) {
        super(id);
        this.name = name;
        this.projects = projects;
        this.status = status;
    }

    public Customer toEntity() {
        return Customer.builder()
                .id(id)
                .name(name)
                .projects(projects.stream()
                        .map(AdminProjectDto::toEntity)
                        .collect(Collectors.toList()))
                .status(status)
                .build();
    }

    public static AdminCustomerDto toDto(Customer customer) {
        return AdminCustomerDto.builder()
                .id(customer.getId())
                .name(customer.getName())
                .projects(customer.getProjects().stream()
                        .map(AdminProjectDto::toDto)
                        .collect(Collectors.toList()))
                .status(customer.getStatus())
                .build();
    }
}