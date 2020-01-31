package com.minakov.springsecurityproject.rest;

import com.minakov.springsecurityproject.dto.UserDto;
import com.minakov.springsecurityproject.dto.UserRegistrationDto;
import com.minakov.springsecurityproject.model.User;
import com.minakov.springsecurityproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author Yaroslav Minakov
 */
@RestController
@RequestMapping("/api/v1/auth/")
public class UserRegistrationRestControllerV1 {

    private final UserService userService;

    @Autowired
    public UserRegistrationRestControllerV1(UserService userService) {
        this.userService = userService;
    }


    @PostMapping(value = "registration", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity registration(@RequestBody @Valid UserRegistrationDto userRegistrationDto, BindingResult bindingResult) {

        if (userRegistrationDto == null) {
            return  new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        if (bindingResult.hasErrors()) {
            return new ResponseEntity(HttpStatus.FORBIDDEN);
        }

        User user = userRegistrationDto.toEntity();
        User registeredUser = this.userService.registration(user);
        UserDto userDto = UserDto.toDto(registeredUser);

        return ResponseEntity.ok(userDto);
    }
}
