package com.minakov.springsecurityproject.service.impl;

import com.minakov.springsecurityproject.model.User;
import com.minakov.springsecurityproject.repository.UserRepository;
import com.minakov.springsecurityproject.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Yaroslav Minakov
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User findByUsername(String username) {
        User result = this.userRepository.findByUsername(username);
        log.info("IN findByUsername - user: {} found by username: {}", result, username);
        return result;
    }

    @Override
    public User findById(Long userId) {
        User result = this.userRepository.findById(userId).orElse(null);
        log.info("IN findById - user: {} found by id: {}", result, userId);
        return result;
    }

    @Override
    public List<User> findAll() {
        List<User> result = this.userRepository.findAll();
        log.info("IN findAll - users: {}", result);
        return result;
    }

    @Override
    @Transactional
    public User save(User user) {
        log.info("IN save - user: {}", user);
        return this.userRepository.save(user);
    }

    @Override
    @Transactional
    public User registration(User user) {
        log.info("IN registration - user: {}", user);
        user.setPhoneVerified(true);
        return save(user);
    }

    @Override
    public User findByIdFetchSkills(Long userId) {
        User result = this.userRepository.findByIdFetchSkills(userId);
        log.info("IN findByIdFetchSkills - user: {} found by id: {}", result, userId);
        return result;
    }

    @Override
    public List<User> findAllFetchSkills() {
        List<User> result = this.userRepository.findAllFetchSkills();
        log.info("IN findAllFetchSkills - users: {}", result);
        return result;
    }
}
