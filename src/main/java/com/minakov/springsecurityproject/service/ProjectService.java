package com.minakov.springsecurityproject.service;

import com.minakov.springsecurityproject.model.Project;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author Yaroslav Minakov
 */
public interface ProjectService {

    Project findById(Long projectId);
    List<Project> findAll();
    Project save(Project project);
    List<Project> findAllFetchTeams();
    Project findByIdFetchTeams(Long projectId);
}
