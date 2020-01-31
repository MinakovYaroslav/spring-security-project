package com.minakov.springsecurityproject.model;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Yaroslav Minakov
 */
@Entity
@Table(name = "projects")
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class Project extends AbstractEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "budget")
    private BigDecimal budget;

    @ManyToMany
    @JoinTable(name = "project_teams",
            joinColumns = {@JoinColumn(name = "project_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "team_id", referencedColumnName = "id")})
    private List<Team> teams;

    @ManyToMany(mappedBy = "projects")
    private List<Customer> customers;

    @Builder
    public Project(Long id,
                   LocalDateTime created,
                   LocalDateTime updated,
                   Boolean status, String name,
                   BigDecimal budget, List<Team> teams,
                   List<Customer> customers) {
        super(id, created, updated, status);
        this.name = name;
        this.budget = budget;
        this.teams = teams;
        this.customers = customers;
    }

    @Override
    public String toString() {
        return "Project{" +
                "name='" + name + '\'' +
                ", budget=" + budget +
                ", id=" + id +
                ", created=" + created +
                ", updated=" + updated +
                ", status=" + status +
                '}';
    }
}
