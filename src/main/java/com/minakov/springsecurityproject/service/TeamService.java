package com.minakov.springsecurityproject.service;

import com.minakov.springsecurityproject.model.Team;

import java.util.List;

/**
 * @author Yaroslav Minakov
 */
public interface TeamService {

    Team findById(Long teamId);
    List<Team> findAll();
    Team save(Team team);
    List<Team> findAllFetchUsers();
    Team findByIdFetchUsers(Long teamId);
}
