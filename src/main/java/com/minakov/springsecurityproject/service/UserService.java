package com.minakov.springsecurityproject.service;

import com.minakov.springsecurityproject.model.User;

import java.util.List;

/**
 * @author Yaroslav Minakov
 */
public interface UserService {

    User findByUsername(String username);
    User findById(Long userId);
    List<User> findAll();
    User save(User user);
    User registration(User user);
    User findByIdFetchSkills(Long userId);
    List<User> findAllFetchSkills();
}
