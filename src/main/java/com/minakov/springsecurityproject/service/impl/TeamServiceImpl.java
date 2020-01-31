package com.minakov.springsecurityproject.service.impl;

import com.minakov.springsecurityproject.model.Team;
import com.minakov.springsecurityproject.repository.TeamRepository;
import com.minakov.springsecurityproject.service.TeamService;
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
public class TeamServiceImpl implements TeamService {

    private final TeamRepository teamRepository;

    @Autowired
    public TeamServiceImpl(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    @Override
    public Team findById(Long teamId) {
        Team result = this.teamRepository.findById(teamId).orElse(null);
        log.info("IN save - team: {} ", result);
        return result;
    }

    @Override
    public List<Team> findAll() {
        List<Team> result = this.teamRepository.findAll();
        log.info("IN findAll - teams: {}", result);
        return result;
    }

    @Override
    @Transactional
    public Team save(Team team) {
        Team result = this.teamRepository.save(team);
        log.info("IN save - team: {} ", result);
        return result;
    }
}
