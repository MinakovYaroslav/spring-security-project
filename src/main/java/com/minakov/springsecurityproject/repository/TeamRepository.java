package com.minakov.springsecurityproject.repository;

import com.minakov.springsecurityproject.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Yaroslav Minakov
 */
@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {
}
