package com.minakov.springsecurityproject.repository;

import com.minakov.springsecurityproject.model.Skill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Yaroslav Minakov
 */
@Repository
public interface SkillRepository extends JpaRepository<Skill, Long> {
}
