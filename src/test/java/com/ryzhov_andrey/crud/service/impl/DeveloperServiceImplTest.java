package com.ryzhov_andrey.crud.service.impl;

import com.ryzhov_andrey.crud.model.Developer;
import com.ryzhov_andrey.crud.model.Status;
import com.ryzhov_andrey.crud.repository.DeveloperRepository;
import com.ryzhov_andrey.crud.repository.impl.DeveloperRepositoryImpl;
import com.ryzhov_andrey.crud.service.DeveloperService;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;


@RunWith(MockitoJUnitRunner.class)
class DeveloperServiceImplTest {

    private DeveloperRepository developerRepository = Mockito.mock(DeveloperRepositoryImpl.class);
    private DeveloperService serviceUnderTest = new DeveloperServiceImpl(developerRepository);


    private List<Developer> getDevelopers() {
        return List.of(
                new Developer(1L, "AAAAA", "BBBBB", new ArrayList<>(), null, Status.ACTIVE),
                new Developer(2L, "CCCCC", "DDDDD", new ArrayList<>(), null, Status.DELETED),
                new Developer(3L, "EEEEE", "FFFFF", new ArrayList<>(), null, Status.DELETED),
                new Developer(4L, "GGGGG", "HHHHH", new ArrayList<>(), null, Status.ACTIVE)
        );
    }

    private Developer getDeveloper() {
        return new Developer(1L, "faeeaf", "aefaefea", new ArrayList<>(), null, Status.ACTIVE);
    }

    @Test
    void getById() {
        doReturn(getDeveloper()).when(developerRepository).getById(anyLong());
        Developer developer = serviceUnderTest.getById(1L);
        assertEquals(developer, getDeveloper());
    }

    @Test
    public void getAllActive() {
        doReturn(getDevelopers()).when(developerRepository).getAll();
        List<Developer> activeDeveloper = serviceUnderTest.getAll();
        assertTrue(activeDeveloper.stream().anyMatch(d -> d.getStatus().equals(Status.ACTIVE)));
    }

    @Test
    void create() {
        doReturn(getDeveloper()).when(developerRepository).create(getDeveloper());
        Developer developer = serviceUnderTest.create(getDeveloper());
        assertEquals(developer, developerRepository.create(getDeveloper()));
    }

    @Test
    void update() {
        doReturn(getDeveloper()).when(developerRepository).update(getDeveloper());
        Developer developer = serviceUnderTest.update(getDeveloper());
        assertEquals(developer, developerRepository.update(getDeveloper()));
    }

    @Test
    void deleteById() {
        developerRepository.deleteById(anyLong());
        verify(developerRepository).deleteById(anyLong());
    }
}