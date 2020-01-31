package com.minakov.springsecurityproject;

import com.minakov.springsecurityproject.model.Role;
import com.minakov.springsecurityproject.model.Skill;
import com.minakov.springsecurityproject.model.User;
import com.minakov.springsecurityproject.repository.RoleRepository;
import com.minakov.springsecurityproject.repository.SkillRepository;
import com.minakov.springsecurityproject.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class SpringSecurityApplicationTests {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SkillRepository skillRepository;

    @Test
    void createRole() {
        Role role = new Role();
        role.setName("ROLE_GOD");
        role = roleRepository.save(role);

        assertNotNull(role.getStatus());
    }

    @Test
    void getUser() {
        User user = this.userRepository.findAll().get(0);
        List<Optional<Skill>> skills = user.getSkills().stream()
                .map(skill -> skillRepository.findById(skill.getId()))
                .collect(Collectors.toList());
        System.out.println(skills);
    }
}