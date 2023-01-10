package com.ryzhov_andrey.crud.service.impl;

import com.ryzhov_andrey.crud.model.Skill;
import com.ryzhov_andrey.crud.model.Specialty;
import com.ryzhov_andrey.crud.model.Status;
import com.ryzhov_andrey.crud.repository.SkillRepository;
import com.ryzhov_andrey.crud.repository.SpecialtyRepository;
import com.ryzhov_andrey.crud.repository.jdbc.JdbcSkillRepositoryImpl;
import com.ryzhov_andrey.crud.repository.jdbc.JdbcSpecialtyRepositoryImpl;
import com.ryzhov_andrey.crud.service.SkillService;
import com.ryzhov_andrey.crud.service.SpecialtyService;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
class SpecialtyServiceImplTest {

    private SpecialtyRepository specialtyRepository = Mockito.mock(JdbcSpecialtyRepositoryImpl.class);
    private SpecialtyService serviceUnderTest = new SpecialtyServiceImpl(specialtyRepository);
    private List<Specialty> getSpecialties() {
        return List.of(
                new Specialty(  1L ,"AAA" , Status.ACTIVE),
                new Specialty(  2L ,"BBB" ,Status.DELETED),
                new Specialty(  3L ,"CCC" ,Status.ACTIVE),
                new Specialty(  4L ,"DDD" ,Status.ACTIVE)

        );
    }

    private Specialty getSpecialty() {
        return new Specialty(1L, "XXX", Status.ACTIVE);
    }

    @Test
    void getById() {
        doReturn(getSpecialty()).when(specialtyRepository).getById(anyLong());
        Specialty specialty= serviceUnderTest.getById(1L);
        assertEquals(specialty, getSpecialty());
    }

    @Test
    void getAll() {
        doReturn(getSpecialties()).when(specialtyRepository).getAll();
        List<Specialty> activeSpecialty = serviceUnderTest.getAll();
        assertTrue(activeSpecialty.stream().anyMatch(d -> d.getStatus().equals(Status.ACTIVE)));
    }

    @Test
    void create() {
        doReturn(getSpecialty()).when(specialtyRepository).create(getSpecialty());
        Specialty specialty = serviceUnderTest.create(getSpecialty());
        assertEquals(specialty, specialtyRepository.create(getSpecialty()));
    }

    @Test
    void update() {
        doReturn(getSpecialty()).when(specialtyRepository).update(getSpecialty());
        Specialty specialty = serviceUnderTest.update(getSpecialty());
        assertEquals(specialty, specialtyRepository.update(getSpecialty()));
    }

    @Test
    void deleteById() {
        specialtyRepository.deleteById(anyLong());
        verify(specialtyRepository).deleteById(anyLong());
    }
}