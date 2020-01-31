package com.minakov.springsecurityproject.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Yaroslav Minakov
 */
@Entity
@Table(name = "skills")
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class Skill extends AbstractEntity {

    @Column(name = "name")
    private String name;

    @ManyToMany(mappedBy = "skills")
    private List<User> users;

    @Builder
    public Skill(Long id,
                 LocalDateTime created,
                 LocalDateTime updated,
                 Boolean status, String name,
                 List<User> users) {
        super(id, created, updated, status);
        this.name = name;
        this.users = users;
    }

    @Override
    public String toString() {
        return "Skill{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", created=" + created +
                ", updated=" + updated +
                ", status=" + status +
                '}';
    }
}
