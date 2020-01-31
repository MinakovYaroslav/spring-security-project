package com.minakov.springsecurityproject.service;

import com.minakov.springsecurityproject.model.Team;

import java.util.List;

/**
 * @author Yaroslav Minakov
 */
public interface TeamService {

    Team findById(Long teamId);
    List<Team> findAll(); //TODO FIND ALL BY STATUS
    Team save(Team team); //TODO CREATE IN LIQUIBASE DEFAULT BOOLEAN STATUS TRUE
    //TODO CHECK destination.setUpdated(team.getUpdated());
}
