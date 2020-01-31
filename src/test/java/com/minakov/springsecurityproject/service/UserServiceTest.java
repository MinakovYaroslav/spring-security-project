package com.minakov.springsecurityproject.service;

import com.minakov.springsecurityproject.model.User;
import com.minakov.springsecurityproject.service.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;


/**
 * @author Yaroslav Minakov
 */
class UserServiceTest {

    @Mock
    private UserServiceImpl userService;

    @Spy
    private User user;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);

        user = User.builder()
                .id(6L)
                .firstName("Ivan")
                .lastName("Pavlov")
                .password("password")
                .phoneNumber("+0123456789")
                .build();
    }

    @Test
    void saveTest() {
        when(userService.save(user)).thenReturn(user);
    }

    @Test
    void mockCreationTest() {
        assertNotNull(userService);
    }

    @Test
    void findUserByUsernameTest() {
        when(userService.findByUsername(anyString())).thenReturn(new User());
    }

    @Test
    void findAllTest() {
        when(userService.findAll()).thenReturn(anyList());
    }

    @Test
    void findByIdTest() {
        when(userService.findById(user.getId())).thenReturn(user);
    }
}