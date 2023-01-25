package com.ryzhov_andrey.crud.controller;

import com.ryzhov_andrey.crud.model.Specialty;
import com.ryzhov_andrey.crud.model.Status;
import com.ryzhov_andrey.crud.repository.SpecialtyRepository;
import com.ryzhov_andrey.crud.repository.impl.SpecialtyRepositoryImpl;


import java.util.List;

public class SpecialtyController {

    private final SpecialtyRepository specialtyRepository = new SpecialtyRepositoryImpl();

    public Specialty createSpecialty(String name, Status status) {
        Specialty specialty = new Specialty(null, name, status);
        return specialtyRepository.create(specialty);
    }

    public Specialty updateSpecialty(Long id, String name, Status status) {
        Specialty specialty = new Specialty(id, name, status);
        return specialtyRepository.update(specialty);
    }

    public void deleteSpecialty(Long id) {
        Specialty specialty = new Specialty(id, null);
        specialtyRepository.deleteById(specialty.getId());
    }

    public List<Specialty> getAllSpecialties() {
        return specialtyRepository.getAll();
    }

    public Specialty getSpecialtyById(Long id) {
        return specialtyRepository.getById(id);
    }
}
