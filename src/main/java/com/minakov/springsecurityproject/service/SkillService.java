package com.minakov.springsecurityproject.service;

import com.minakov.springsecurityproject.model.Skill;

import java.util.List;

/**
 * @author Yaroslav Minakov
 */
public interface SkillService {

    Skill save(Skill skill);
    List<Skill> findAll();
    Skill findById(Long skillId);
}
