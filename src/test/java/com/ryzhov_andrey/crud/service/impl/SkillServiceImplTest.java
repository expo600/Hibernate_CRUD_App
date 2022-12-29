package com.ryzhov_andrey.crud.service.impl;

import com.ryzhov_andrey.crud.model.Skill;
import com.ryzhov_andrey.crud.repository.SkillRepository;
import com.ryzhov_andrey.crud.repository.jdbc.JdbcSkillRepositoryImpl;
import com.ryzhov_andrey.crud.service.SkillService;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;


@RunWith(MockitoJUnitRunner.class)
class SkillServiceImplTest {

    private static final Long id = 1L;
    private static final String skill_name = "Algorithms";

    @Mock
    private SkillRepository skillRepository;

    @Mock
    private List<Skill> skillList;

    @Spy
    private SkillService skillService;


    @Before
    public void setUp() {
        skillRepository = new JdbcSkillRepositoryImpl();
    }

    @Test
    void getById() {
        doReturn(skillRepository.getById(id)).when(skillService).getById(id);
        assertEquals(skillRepository.getById(id), skillService.getById(1l));
    }

    @Test
    void getAll() {
        doReturn(skillList).when(skillService).getAll();
        assertEquals(skillList, skillService.getAll());
    }

    @Test
    void create() {
        doReturn(skillRepository.create(new Skill(skill_name))).when(skillService).create(new Skill(skill_name));
        assertEquals(skillRepository.create(new Skill(skill_name)), skillService.create(new Skill("str")));
    }

    @Test
    void update() {
        doReturn(skillRepository.update(new Skill(skill_name))).when(skillService).update(new Skill(skill_name));
        assertEquals(skillRepository.update(new Skill(skill_name)), skillService.update(new Skill("str") ));
    }

    @Test
    void deleteById() {
        skillService.deleteById(anyLong());
        verify(skillService).deleteById(anyLong());
    }
}