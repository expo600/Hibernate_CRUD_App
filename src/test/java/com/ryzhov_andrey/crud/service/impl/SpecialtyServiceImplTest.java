package com.ryzhov_andrey.crud.service.impl;

import com.ryzhov_andrey.crud.model.Specialty;
import com.ryzhov_andrey.crud.service.SpecialtyService;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
class SpecialtyServiceImplTest {

    private static final Long id = 1L;
    private static final String specialty_name = "Java";


    @Mock
    private Specialty specialty;

    @Mock
    private List<Specialty> specialtyList;

    @Spy
    private SpecialtyService specialtyService;

    @Before
    public void setUp() {
        String str = "Python";
        when(specialty.getName()).thenReturn(str);
    }

    @Test
    void getById() {
        doReturn(specialty).when(specialtyService).getById(id);
        assertEquals(specialty, specialtyService.getById(1L));
    }


    @Test
    void getAll() {
        doReturn(specialtyList).when(specialtyService).getAll();
        assertEquals(specialtyList, specialtyService.getAll());
    }

    @Test
    void create() {
        doReturn(specialty).when(specialtyService).create(new Specialty("Java"));
        assertEquals(specialty, specialtyService.create(new Specialty(specialty_name)));
    }

    @Test
    void update() {
        doReturn(specialty).when(specialtyService).update(new Specialty("Java"));
        assertEquals(specialty, specialtyService.update(new Specialty(specialty_name)));
    }

    @Test
    void deleteById() {
        specialtyService.deleteById(anyLong());
        verify(specialtyService).deleteById(anyLong());
    }
}