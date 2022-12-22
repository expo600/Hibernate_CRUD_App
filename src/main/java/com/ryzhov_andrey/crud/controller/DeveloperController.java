package com.ryzhov_andrey.crud.controller;

import com.ryzhov_andrey.crud.model.Developer;
import com.ryzhov_andrey.crud.model.Skill;
import com.ryzhov_andrey.crud.model.Specialty;
import com.ryzhov_andrey.crud.model.Status;
import com.ryzhov_andrey.crud.repository.DeveloperRepository;
import com.ryzhov_andrey.crud.repository.jdbc.JdbcDeveloperRepositoryImpl;

import java.util.List;

public class DeveloperController {

    private final DeveloperRepository developerRepository = new JdbcDeveloperRepositoryImpl();


    public Developer createDeveloper(String firstname,
                                     String lastName,
                                     List<Skill> skills,
                                     Specialty specialty,
                                     Status status) {
        Developer developer = new Developer(null, firstname, lastName, skills, specialty, status);
        return developerRepository.save(developer);
    }

    public Developer updateDeveloper(Long id,
                                     String firstname,
                                     String lastName,
                                     List<Skill> skills,
                                     Specialty specialty,
                                     Status status) {
        Developer developer = new Developer(id, firstname, lastName, skills, specialty,status);
        return developerRepository.update(developer);
    }

    public void deleteDeveloper(Long id) {
        Developer developer = new Developer(id, null, null, null, null);
        developerRepository.deleteById(developer.getId());
    }

    public List<Developer> getAllDevelopers() {
        return developerRepository.getAll();
    }

    public Developer getDeveloperById(Long id) {
        Developer developer = new Developer(id, null, null, null, null);
        return developerRepository.getById(developer.getId());
    }
}

