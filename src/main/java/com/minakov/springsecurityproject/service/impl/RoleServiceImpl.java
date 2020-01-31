package com.minakov.springsecurityproject.service.impl;

import com.minakov.springsecurityproject.model.Role;
import com.minakov.springsecurityproject.repository.RoleRepository;
import com.minakov.springsecurityproject.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Yaroslav Minakov
 */
@Slf4j
@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    @Transactional
    public Role save(Role role) {
        Role result = this.roleRepository.save(role);
        log.info("IN save - role: {} ", result);
        return result;
    }

    @Override
    public Role findById(Long roleId) {
        Role result = this.roleRepository.findById(roleId).orElse(null);
        log.info("IN findById - role: {} found by id: {}", result, roleId);
        return result;
    }

    @Override
    public List<Role> findAll() {
        List<Role> result = this.roleRepository.findAll();
        log.info("IN findAll - roles: {}", result);
        return result;
    }
}
