package com.ryzhov_andrey.crud.service.impl;

import com.ryzhov_andrey.crud.model.Skill;
import com.ryzhov_andrey.crud.repository.SkillRepository;
import com.ryzhov_andrey.crud.repository.jdbc.JdbcSkillRepositoryImpl;
import com.ryzhov_andrey.crud.service.SkillService;

import java.util.List;

public class SkillServiceImpl implements SkillService {

    private final SkillRepository skillRepository;

    public SkillServiceImpl() {
        this.skillRepository = new JdbcSkillRepositoryImpl();
    }

    @Override
    public Skill getById(Long id) {
        return skillRepository.getById(id);
    }

    @Override
    public List<Skill> getAll() {
        return skillRepository.getAll();
    }

    @Override
    public Skill create(Skill skill) {
        return skillRepository.create(skill);
    }

    @Override
    public Skill update(Skill skill) {
        return skillRepository.update(skill);
    }

    @Override
    public void deleteById(Long id) {
        skillRepository.deleteById(id);
    }
}
