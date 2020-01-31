package com.minakov.springsecurityproject.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Yaroslav Minakov
 */
@Entity
@Table(name = "customers")
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class Customer extends AbstractEntity {

    @Column(name = "name")
    private String name;

    @ManyToMany
    @JoinTable(name = "customer_projects",
            joinColumns = {@JoinColumn(name = "customer_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "project_id", referencedColumnName = "id")})
    private List<Project> projects;

    @Builder
    public Customer(Long id,
                    LocalDateTime created,
                    LocalDateTime updated,
                    Boolean status, String name,
                    List<Project> projects) {
        super(id, created, updated, status);
        this.name = name;
        this.projects = projects;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", created=" + created +
                ", updated=" + updated +
                ", status=" + status +
                '}';
    }
}
