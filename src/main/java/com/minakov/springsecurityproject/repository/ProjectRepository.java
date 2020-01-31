package com.minakov.springsecurityproject.repository;

import com.minakov.springsecurityproject.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Yaroslav Minakov
 */
@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {

    @Query("FROM Project p JOIN FETCH p.teams")
    List<Project> findAllFetchTeams();

    @Query("FROM Project p JOIN FETCH p.teams WHERE p.id =:projectId")
    Project findByIdFetchTeams(@Param("projectId") Long projectId);
}
