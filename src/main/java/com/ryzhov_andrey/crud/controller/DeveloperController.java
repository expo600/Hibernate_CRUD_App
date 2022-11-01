package com.ryzhov_andrey.crud.controller;

import com.ryzhov_andrey.crud.model.Developer;
import com.ryzhov_andrey.crud.model.Skill;
import com.ryzhov_andrey.crud.model.Specialty;
import com.ryzhov_andrey.crud.repository.DeveloperRepository;
import com.ryzhov_andrey.crud.repository.gson.GsonDeveloperRepositoryImp;

import java.util.List;

public class DeveloperController {

    private final DeveloperRepository developerRepository = new GsonDeveloperRepositoryImp();


    public Developer createDeveloper(String name,
                                     String lastName,
                                     List<Skill> skills,
                                     Specialty specialty) throws Exception {
        Developer developer = new Developer(null, name, lastName, skills, specialty);
        return developerRepository.save(developer);
    }

    public Developer updateDeveloper(Long id,
                                     String name,
                                     String lastName,
                                     List<Skill> skills,
                                     Specialty specialty) throws Exception {
        Developer developer = new Developer(id, name, lastName, skills, specialty);
        return developerRepository.update(developer);
    }

    public void deleteDeveloper(Long id) throws Exception {
        Developer developer = new Developer(id, null, null, null, null);
        developerRepository.deleteById(developer.getId());
    }

    public List<Developer> getAllDevelopers() throws Exception {
        return developerRepository.getAll();
    }

    public Developer getDeveloperById(Long id){
        Developer developer = new Developer(id,null,null,null,null);
        return developerRepository.getById(developer.getId());
    }
}
