package com.minakov.springsecurityproject.repository;

import com.minakov.springsecurityproject.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Yaroslav Minakov
 */
@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {

    @Query("FROM Team t JOIN FETCH t.users")
    List<Team> findAllFetchUsers();

    @Query("FROM Team t JOIN FETCH t.users WHERE t.id =:teamId")
    Team findByIdFetchUsers(@Param("teamId") Long teamId);
}
