package com.ryzhov_andrey.crud.service.impl;

import com.ryzhov_andrey.crud.model.Developer;
import com.ryzhov_andrey.crud.model.Skill;
import com.ryzhov_andrey.crud.model.Status;
import com.ryzhov_andrey.crud.repository.DeveloperRepository;
import com.ryzhov_andrey.crud.repository.SkillRepository;
import com.ryzhov_andrey.crud.repository.jdbc.JdbcDeveloperRepositoryImpl;
import com.ryzhov_andrey.crud.repository.jdbc.JdbcSkillRepositoryImpl;
import com.ryzhov_andrey.crud.service.DeveloperService;
import com.ryzhov_andrey.crud.service.SkillService;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;


@RunWith(MockitoJUnitRunner.class)
class SkillServiceImplTest {

    private SkillRepository skillRepository = Mockito.mock(JdbcSkillRepositoryImpl.class);
    private SkillService serviceUnderTest = new SkillServiceImpl(skillRepository);


    private List<Skill> getSkills() {
        return List.of(
                new Skill(  1L ,"AAAAA" ,Status.ACTIVE),
                new Skill(  2L ,"BBBBB" ,Status.DELETED),
                new Skill(  3L ,"CCCCC" ,Status.ACTIVE),
                new Skill(  4L ,"DDDDD" ,Status.ACTIVE)

        );
    }

    private Skill getSkill() {
        return new Skill(1L, "XXXX", Status.ACTIVE);
    }


    @Test
    void getById() {
        doReturn(getSkill()).when(skillRepository).getById(anyLong());
        Skill skill = serviceUnderTest.getById(1L);
        assertEquals(skill, getSkill());
    }

    @Test
    void getAll() {
        doReturn(getSkills()).when(skillRepository).getAll();
        List<Skill> activeSkill = serviceUnderTest.getAll();
        assertTrue(activeSkill.stream().anyMatch(d -> d.getStatus().equals(Status.ACTIVE)));
    }

    @Test
    void create() {
        doReturn(getSkill()).when(skillRepository).create(getSkill());
        Skill skill = serviceUnderTest.create(getSkill());
        assertEquals(skill, skillRepository.create(getSkill()));
    }

    @Test
    void update() {
        doReturn(getSkill()).when(skillRepository).update(getSkill());
        Skill skill = serviceUnderTest.update(getSkill());
        assertEquals(skill, skillRepository.update(getSkill()));
    }

    @Test
    void deleteById() {
        skillRepository.deleteById(anyLong());
        verify(skillRepository).deleteById(anyLong());
    }
}