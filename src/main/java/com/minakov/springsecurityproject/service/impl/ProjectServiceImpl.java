package com.minakov.springsecurityproject.service.impl;

import com.minakov.springsecurityproject.model.Project;
import com.minakov.springsecurityproject.repository.ProjectRepository;
import com.minakov.springsecurityproject.service.ProjectService;
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
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;

    @Autowired
    public ProjectServiceImpl(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @Override
    public Project findById(Long projectId) {
        Project result = this.projectRepository.findById(projectId).orElse(null);
        log.info("IN findById - project: {} found by id: {}", result, projectId);
        return result;
    }

    @Override
    public List<Project> findAll() {
        List<Project> result = this.projectRepository.findAll();
        log.info("IN findAll - projects: {}", result);
        return result;
    }

    @Override
    @Transactional
    public Project save(Project project) {
        Project result = this.projectRepository.save(project);
        log.info("IN save - project: {} ", result);
        return result;
    }

    @Override
    public List<Project> findAllFetchTeams() {
        List<Project> result = this.projectRepository.findAllFetchTeams();
        log.info("IN findAllFetchTeams - projects: {}", result);
        return result;
    }

    @Override
    public Project findByIdFetchTeams(Long projectId) {
        Project result = this.projectRepository.findByIdFetchTeams(projectId);
        log.info("IN findByIdFetchTeams - project: {} found by id: {}", result, projectId);
        return result;
    }
}
