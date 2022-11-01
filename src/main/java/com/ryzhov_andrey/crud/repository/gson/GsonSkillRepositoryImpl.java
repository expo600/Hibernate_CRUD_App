package com.ryzhov_andrey.crud.repository.gson;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ryzhov_andrey.crud.model.Skill;
import com.ryzhov_andrey.crud.repository.SkillRepository;

import java.io.*;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class GsonSkillRepositoryImpl implements SkillRepository {

    private final static String pathToFile = "\\src\\main\\resources\\";
    private final static String FILE_NAME = "skills.json";
    private final Gson GSON = new Gson();


    @Override
    public Skill getById(Long id) {
        return getAllSkills().stream().filter(s -> s.getId().equals(id)).findFirst().orElse(null);
    }

    @Override
    public List<Skill> getAll() {
        return getAllSkills();
    }

    @Override
    public Skill save(Skill skill) {
        List<Skill> existingSkills = getAllSkills();
        skill.setId(autoIncrementId(existingSkills));
        existingSkills.add(skill);
        writeSkillToFile(existingSkills);
        return skill;
    }

    @Override
    public Skill update(Skill skill) {
        List<Skill> existingSkills = getAllSkills();
        existingSkills.forEach(existingSkill -> {
            if(existingSkill.getId().equals(skill.getId())) {
                existingSkill.setName(skill.getName());
            }
        });
        writeSkillToFile(existingSkills);
        return skill;
    }

    @Override
    public void deleteById(Long id) {
        List<Skill> existingSkills = getAllSkills();
        existingSkills.removeIf(s -> s.getId().equals(id));
        writeSkillToFile(existingSkills);
    }

    private List<Skill> getAllSkills() {
        try {
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(
                            new FileInputStream(Paths.get("").toAbsolutePath() + pathToFile + FILE_NAME),
                            StandardCharsets.UTF_8));
            String str = reader
                    .lines()
                    .collect(Collectors.joining(System.lineSeparator()));

            Type type = new TypeToken<List<Skill>>() {
            }.getType();
            return GSON.fromJson(str, type);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    private void writeSkillToFile(List<Skill> skills) {
        String jsonCollection = GSON.toJson(skills);
        try (BufferedWriter bw = new BufferedWriter(new FileWriter
                (Paths.get("").toAbsolutePath() + pathToFile + FILE_NAME, false))) {
            bw.write(jsonCollection);
            bw.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Long autoIncrementId(List<Skill> skills) {
        Skill maxIdSkill = skills.stream().max(Comparator.comparing(Skill::getId)).orElse(null);
        return Objects.nonNull(maxIdSkill) ? maxIdSkill.getId() + 1 : 1;
    }
}