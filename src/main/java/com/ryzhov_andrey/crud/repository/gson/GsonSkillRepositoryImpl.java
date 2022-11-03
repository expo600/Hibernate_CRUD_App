package com.ryzhov_andrey.crud.repository.gson;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ryzhov_andrey.crud.model.Skill;
import com.ryzhov_andrey.crud.model.Status;
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
                existingSkill.setStatus(Status.ACTIVE);
            }
        });
        writeSkillToFile(existingSkills);
        return skill;
    }

    @Override
    public void deleteById(Long id) {
        List<Skill> existingSkills = getAllSkills();
       Skill findSkill = existingSkills .stream()
                                        .filter(s -> s.getId().equals(id))
                                        .findFirst().orElse(null);
       findSkill.setStatus(Status.DELETED);
        writeSkillToFile(existingSkills);
    }

    private List<Skill> getAllSkills() {
        try(FileInputStream fileInputStream = new FileInputStream(Paths.get("").toAbsolutePath() + pathToFile + FILE_NAME)) {
            String str = new String(fileInputStream.readAllBytes());
            Type type = new TypeToken<List<Skill>>() {}.getType();
            return GSON.fromJson(str, type);
        } catch (IOException e) {
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