package com.ryzhov_andrey.crud.service.impl;

import com.ryzhov_andrey.crud.model.Developer;
import com.ryzhov_andrey.crud.service.DeveloperService;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;


@RunWith(MockitoJUnitRunner.class)
class DeveloperServiceImplTest {


    private static final Long id = 1L;
    private static final String first_name = "Petr";
    private static final String last_name = "Petrov";


    @Mock
    private Developer developer;

    @Mock
    private List<Developer> developerList;

    @Spy
    private DeveloperService developerService;


    @Before
    public void setUp() {

        String firstName = "Ivan";
        String lastName = "Ivanov";

        when(developer.getFirstName()).thenReturn(firstName);
        when(developer.getLastName()).thenReturn(lastName);
    }

    @Test
    public void getDeveloperTest() {
        assertEquals("Ivan", developer.getFirstName());
        assertEquals("Ivanov", developer.getLastName());
    }

    @Test
    void getById() {
        doReturn(developer).when(developerService).getById(id);
        assertEquals(developer, developerService.getById(1L));
    }

    @Test
    void getAll() {
        doReturn(developerList).when(developerService).getAll();
        assertEquals(developerList, developerService.getAll());
    }

    @Test
    void create() {
        doReturn(developer).when(developerService).create(new Developer(first_name, last_name));
        assertEquals(developer, developerService.create(new Developer("Petr", "Petrov")));
    }

    @Test
    void update() {
        doReturn(developer).when(developerService).update(new Developer("Petr", "Petrov"));
        assertEquals(developer, developerService.update(new Developer(first_name, last_name)));
    }

    @Test
    void deleteById() {
        developerService.deleteById(anyLong());
        verify(developerService).deleteById(anyLong());
    }
}