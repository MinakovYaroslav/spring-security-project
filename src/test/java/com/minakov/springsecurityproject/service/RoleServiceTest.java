package com.minakov.springsecurityproject.service;

import com.minakov.springsecurityproject.dto.admin.AdminRoleDto;
import com.minakov.springsecurityproject.model.Role;
import com.minakov.springsecurityproject.repository.RoleRepository;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Yaroslav Minakov
 */
@SpringBootTest
class RoleServiceTest {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Test
    void saveRole() {
//        Role roleFromRepo = this.roleRepository.findById(1L).get();
//
//        Role roleFromDto = new Role();
//
//        roleFromDto.setId(1L);
//        roleFromDto.setName("USER_USER_ROLE");
//        roleFromDto.setStatus(false);
//
//        this.roleRepository.save(roleFromDto);
//
//        Role roleFromRepoAfterUpdate = this.roleRepository.findById(1L).get();
//
//        System.out.println();
    }

    @Test
    void saveRoleDto() {
        Role roleFromRepo = this.roleRepository.findById(1L).get();

        AdminRoleDto adminRoleDto = this.modelMapper.map(roleFromRepo, AdminRoleDto.class);

        adminRoleDto.setName("FOOD_ROLE");
        adminRoleDto.setStatus(false);

        Role roleFromDto = modelMapper.map(adminRoleDto, Role.class);

        this.roleRepository.save(roleFromDto);

        Role roleFromRepoAfterUpdate = this.roleRepository.findById(1L).get();
        System.out.println();

    }
}