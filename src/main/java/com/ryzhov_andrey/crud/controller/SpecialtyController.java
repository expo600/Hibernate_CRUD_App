package com.ryzhov_andrey.crud.controller;

import com.ryzhov_andrey.crud.model.Skill;
import com.ryzhov_andrey.crud.model.Specialty;
import com.ryzhov_andrey.crud.repository.SpecialtyRepository;
import com.ryzhov_andrey.crud.repository.gson.GsonSpecialtyRepositoryImp;

import java.util.List;

public class SpecialtyController {

    private final SpecialtyRepository specialtyRepository = new GsonSpecialtyRepositoryImp();


    public Specialty createSpecialty(String name) throws Exception {
        Specialty specialty = new Specialty(null, name);
        return specialtyRepository.save(specialty);
    }

    public Specialty updateSpecialty(Long id, String name) throws Exception {
        Specialty specialty = new Specialty(null, name);
        return specialtyRepository.save(specialty);
    }

    public void deleteSpecialty(Long id, String name) throws Exception {
        Specialty specialty = new Specialty(null, name);
        specialtyRepository.deleteById(specialty.getId());
    }

    public List<Specialty> getAllSpecialties() throws Exception {
        return specialtyRepository.getAll();
    }

    public Specialty getSpecialtyById(Long id) {
        Specialty specialty = new Specialty(id, null);
        return specialtyRepository.getById(id);
    }
}
