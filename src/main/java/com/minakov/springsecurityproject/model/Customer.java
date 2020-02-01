package com.minakov.springsecurityproject.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

/**
 * @author Yaroslav Minakov
 */
@Entity
@Table(name = "customers")
@Data
@Builder
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

    @Override
    public String toString() {
        return "Customer{" +
                "name='" + name + '\'' +
                ", id=" + id +
                '}';
    }
}
