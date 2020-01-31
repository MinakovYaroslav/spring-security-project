package com.minakov.springsecurityproject.service;

import com.minakov.springsecurityproject.model.Role;

import java.util.List;

/**
 * @author Yaroslav Minakov
 */
public interface RoleService {

    Role findById(Long roleId);
    List<Role> findAll();
    Role save(Role role);
}
