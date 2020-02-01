package com.minakov.springsecurityproject.repository;

import com.minakov.springsecurityproject.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Yaroslav Minakov
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);

    @Query("FROM User u JOIN FETCH u.skills WHERE u.id =:userId")
    User findByIdFetchSkills(@Param("userId") Long userId);

    @Query("FROM User u JOIN FETCH u.skills ") //TODO returns 12 users instead of 5
    List<User> findAllFetchSkills();
}