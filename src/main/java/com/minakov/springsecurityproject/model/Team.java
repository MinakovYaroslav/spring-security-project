package com.minakov.springsecurityproject.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Yaroslav Minakov
 */
@Entity
@Table(name = "teams")
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class Team extends AbstractEntity {

    @Column(name = "name")
    private String name;

    @ManyToMany
    @JoinTable(name = "team_users",
            joinColumns = {@JoinColumn(name = "team_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")})
    private List<User> users;

    @ManyToMany(mappedBy = "teams")
    private List<Project> projects;

    @Builder
    public Team(Long id,
                LocalDateTime created,
                LocalDateTime updated,
                Boolean status, String name,
                List<User> users, List<Project> projects) {
        super(id, created, updated, status);
        this.name = name;
        this.users = users;
        this.projects = projects;
    }

    @Override
    public String toString() {
        return "Team{" +
                "name='" + name + '\'' +
                '}';
    }
}
