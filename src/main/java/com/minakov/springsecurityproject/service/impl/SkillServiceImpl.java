package com.minakov.springsecurityproject.service.impl;

import com.minakov.springsecurityproject.model.Skill;
import com.minakov.springsecurityproject.repository.SkillRepository;
import com.minakov.springsecurityproject.service.SkillService;
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
public class SkillServiceImpl implements SkillService {

    private final SkillRepository skillRepository;

    @Autowired
    public SkillServiceImpl(SkillRepository skillRepository) {
        this.skillRepository = skillRepository;
    }

    @Override
    @Transactional
    public Skill save(Skill skill) {
        Skill result = this.skillRepository.save(skill);
        log.info("IN save - skill: {} ", result);
        return result;
    }

    @Override
    public List<Skill> findAll() {
        List<Skill> result = this.skillRepository.findAll();
        log.info("IN findAll - skills: {}", result);
        return result;
    }

    @Override
    public Skill findById(Long skillId) {
        Skill result = this.skillRepository.findById(skillId).orElse(null);
        log.info("IN findById - skill: {} found by id: {}", result, skillId);
        return result;
    }
}
