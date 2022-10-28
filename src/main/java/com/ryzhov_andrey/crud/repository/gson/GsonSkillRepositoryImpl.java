package com.ryzhov_andrey.crud.repository.gson;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ryzhov_andrey.crud.model.Skill;
import com.ryzhov_andrey.crud.repository.SkillRepository;

import java.io.*;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class GsonSkillRepositoryImpl implements SkillRepository {

    private final static String pathToFile = "\\src\\main\\resources\\";
    private final static String FILE_NAME = "skills.json";

    @Override
    public Skill getById(Integer id) throws IOException {
        List<Skill> skills;
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream(Paths.get("").toAbsolutePath() + pathToFile + FILE_NAME),
                        StandardCharsets.UTF_8));
        String str = reader
                .lines()
                .collect(Collectors.joining(System.lineSeparator()));

        Type type = new TypeToken<List<Skill>>() {
        }.getType();
        skills = new Gson().fromJson(str, type);

        Skill current = null;
        for (Skill s : skills) {
            if (s.getId().equals(id)) {
                current = s;
                break;
            }
        }
        if (current != null) {
            return current;
        }
        throw new IOException();
    }

    @Override
    public List<Skill> getAll() throws IOException {
        List<Skill> skills;
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream(Paths.get("").toAbsolutePath() + pathToFile + FILE_NAME),
                        StandardCharsets.UTF_8));
        String str = reader
                .lines()
                .collect(Collectors.joining(System.lineSeparator()));
        Type type = new TypeToken<List<Skill>>() {
        }.getType();
        skills = new Gson().fromJson(str, type);
        return skills;
    }

    @Override
    public Skill save(Skill s) throws IOException {
        List<Skill> skills;
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream(Paths.get("").toAbsolutePath() + pathToFile + FILE_NAME),
                        StandardCharsets.UTF_8));
        String str = reader
                .lines()
                .collect(Collectors.joining(System.lineSeparator()));
        Type type = new TypeToken<List<Skill>>() {
        }.getType();
        skills = new Gson().fromJson(str, type);
        skills.add(s);
        // сохранить коллекцию в json
        String jsonCollection = new Gson().toJson(skills);
        try (BufferedWriter bw = new BufferedWriter(new FileWriter
                (Paths.get("").toAbsolutePath() + pathToFile + FILE_NAME, false))) {
            bw.write(jsonCollection);
            bw.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return s;
    }

    @Override
    public void deleteById(Integer id) throws IOException {
        List<Skill> skills;
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream(Paths.get("").toAbsolutePath() + pathToFile + FILE_NAME),
                        StandardCharsets.UTF_8));
        String str = reader
                .lines()
                .collect(Collectors.joining(System.lineSeparator()));
        Type type = new TypeToken<List<Skill>>() {
        }.getType();
        skills = new Gson().fromJson(str, type);

        Skill removeById = null;
        for (Skill s : skills
        ) {
            if (s.getId().equals(id)) {
                removeById = s;
                break;
            }
        }
        skills.remove(removeById);
        String jsonCollection = new Gson().toJson(skills);
        // сохранить коллекцию в json
        try (BufferedWriter bw = new BufferedWriter(new FileWriter
                (Paths.get("").toAbsolutePath() + pathToFile + FILE_NAME, false))) {
            bw.write(jsonCollection);
            bw.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Skill update(Skill s) throws IOException {
        deleteById(s.getId());
        save(s);
        return s;
    }
}