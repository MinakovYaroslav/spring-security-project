package com.minakov.springsecurityproject.rest;

import com.minakov.springsecurityproject.dto.admin.AdminUserDto;
import com.minakov.springsecurityproject.model.User;
import com.minakov.springsecurityproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author Yaroslav Minakov
 */
@RestController
@RequestMapping("/api/v1/admin/")
public class AdminRestControllerV1 {

    private final UserService userService;

    @Autowired
    public AdminRestControllerV1(UserService userService) {
        this.userService = userService;
    }

    @PutMapping(value = "users/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AdminUserDto> updateUser(@RequestBody @Valid AdminUserDto adminUserDto, @PathVariable("id") Long userId) {

        if (adminUserDto == null || userId == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        HttpHeaders headers = new HttpHeaders();
        User user = adminUserDto.toEntity();

        this.userService.save(user);

        return new ResponseEntity<>(adminUserDto, headers, HttpStatus.OK);
    }
}