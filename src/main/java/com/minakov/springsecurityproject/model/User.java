package com.minakov.springsecurityproject.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Yaroslav Minakov
 */
@Entity
@Table(name = "users")
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class User extends AbstractEntity {

    @Column(name = "username", unique = true)
    private String username;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "specialty")
    private String specialty;

    @Column(name = "password")
    private String password;

    @Column(name = "phone_number", unique = true)
    private String phoneNumber;

    @Column(name = "phone_verified")
    private Boolean phoneVerified;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")})
    private List<Role> roles;

    @ManyToMany
    @JoinTable(name = "user_skills",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "skill_id", referencedColumnName = "id")})
    private List<Skill> skills;

    @ManyToMany(mappedBy = "users")
    private List<Team> teams;

    @Builder
    public User(Long id,
                LocalDateTime created,
                LocalDateTime updated,
                String username,
                String firstName,
                String lastName,
                String specialty,
                String password,
                String phoneNumber,
                Boolean phoneVerified,
                List<Role> roles,
                List<Skill> skills,
                List<Team> teams) {
        super(id, created, updated);
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.specialty = specialty;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.phoneVerified = phoneVerified;
        this.roles = roles;
        this.skills = skills;
        this.teams = teams;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", specialty='" + specialty + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", phoneVerified=" + phoneVerified +
                ", id=" + id +
                '}';
    }
}