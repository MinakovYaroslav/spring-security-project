package com.minakov.springsecurityproject.rest;

import com.minakov.springsecurityproject.dto.admin.AdminUserDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Yaroslav Minakov
 */
@SpringBootTest
@AutoConfigureMockMvc
class AuthenticationRestControllerV1Test {

//    private AdminUserDto adminUserDto1;
//    private AdminUserDto adminUserDto2;
//    private ModelMapper modelMapper;
//
//    @BeforeEach
//    void setUp() {
//        modelMapper = new ModelMapper();
//        modelMapper.getConfiguration()
//                .setPropertyCondition(Conditions.isNotNull());
//        adminUserDto1 = new AdminUserDto();
//        adminUserDto2 = new AdminUserDto();
//
//        adminUserDto1.setFirstName("firstName1");
//        adminUserDto1.setLastName("lastName1");
//        adminUserDto2.setFirstName("firstName2");
//    }
//
//    @Test
//    void login() {
//        modelMapper.map(adminUserDto1, adminUserDto2);
//        assertEquals("lastName1", adminUserDto2.getLastName());
//        assertEquals("firstName2", adminUserDto2.getFirstName());
//    }
}