package com.ryzhov_andrey.crud.controller;

import com.ryzhov_andrey.crud.model.Skill;
import com.ryzhov_andrey.crud.repository.SkillRepository;
import com.ryzhov_andrey.crud.repository.gson.GsonSkillRepositoryImpl;

import java.util.List;

public class SkillController {

    private final SkillRepository skillRepository = new GsonSkillRepositoryImpl();

    public Skill createSkill(String name) throws Exception {
        Skill skill = new Skill(null, name);
        return skillRepository.save(skill);
    }

    public Skill updateSkill(Long id) throws Exception {
        Skill skill = new Skill(id, null);
        return skillRepository.update(skill);
    }

    public void deleteSkill(Long id) throws Exception {
        Skill skill = new Skill(id, null);
        skillRepository.deleteById(skill.getId());
    }

    public List<Skill> getAllSkills() throws Exception {
        return skillRepository.getAll();
    }

    public Skill getSkillById(Long id) {
        Skill skill = new Skill(id, null);
        return skillRepository.getById(id);
    }
}
