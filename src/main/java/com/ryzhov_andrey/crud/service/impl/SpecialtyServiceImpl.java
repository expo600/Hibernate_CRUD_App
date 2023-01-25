package com.ryzhov_andrey.crud.service.impl;

import com.ryzhov_andrey.crud.model.Specialty;
import com.ryzhov_andrey.crud.repository.SpecialtyRepository;
import com.ryzhov_andrey.crud.repository.impl.SpecialtyRepositoryImpl;
import com.ryzhov_andrey.crud.service.SpecialtyService;

import java.util.List;

public class SpecialtyServiceImpl implements SpecialtyService {
    private final SpecialtyRepository specialtyRepository;

    public SpecialtyServiceImpl(SpecialtyRepository specialtyRepository) {
        this.specialtyRepository = specialtyRepository;
    }

    public SpecialtyServiceImpl() {
        this.specialtyRepository = new SpecialtyRepositoryImpl();
    }

    @Override
    public Specialty getById(Long id) {
        return specialtyRepository.getById(id);
    }

    @Override
    public List<Specialty> getAll() {
        return specialtyRepository.getAll();
    }

    @Override
    public Specialty create(Specialty specialty) {
        return specialtyRepository.create(specialty);
    }

    @Override
    public Specialty update(Specialty specialty) {
        return specialtyRepository.update(specialty);
    }

    @Override
    public void deleteById(Long id) {
        specialtyRepository.deleteById(id);
    }
}
