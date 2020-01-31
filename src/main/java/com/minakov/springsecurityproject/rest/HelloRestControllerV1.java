package com.minakov.springsecurityproject.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Yaroslav Minakov
 */
@RestController
@RequestMapping("/api/v1/")
public class HelloRestControllerV1 {

    @GetMapping("hello")
    public String hello() {
        return "Hello World!";
    }
}
