package com.ryzhov_andrey.crud.repository.gson;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ryzhov_andrey.crud.model.Developer;
import com.ryzhov_andrey.crud.model.Specialty;
import com.ryzhov_andrey.crud.model.Status;
import com.ryzhov_andrey.crud.repository.DeveloperRepository;

import java.io.*;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class GsonDeveloperRepositoryImp implements DeveloperRepository {

    private final static String pathToFile = "\\src\\main\\resources\\";
    private final static String FILE_NAME = "developers.json";
    private final Gson GSON = new Gson();

    @Override
    public Developer getById(Long id) {
        return getAllDevelopers().stream().filter(s -> s.getId().equals(id)).findFirst().orElse(null);
    }

    @Override
    public List<Developer> getAll() {
        return getAllDevelopers();
    }

    @Override
    public Developer save(Developer developer) {
        List<Developer> existingDevelopers = getAllDevelopers();
        developer.setId(autoIncrementId(existingDevelopers));
        existingDevelopers.add(developer);
        writeDeveloperToFile(existingDevelopers);
        return developer;
    }

    @Override
    public Developer update(Developer developer) {
        List<Developer> existingDevelopers = getAllDevelopers();
        existingDevelopers.forEach(existingDeveloper -> {
            if(existingDeveloper.getId().equals(developer.getId())) {
                existingDeveloper.setFirstname(developer.getFirstname());
                existingDeveloper.setLastName(developer.getLastName());
                existingDeveloper.setSkills(developer.getSkills());
                existingDeveloper.setSpecialty(developer.getSpecialty());
                existingDeveloper.setStatus(Status.ACTIVE);
            }
        });
        writeDeveloperToFile(existingDevelopers);
        return developer;
    }

    @Override
    public void deleteById(Long id) {
        List<Developer> existingDevelopers = getAllDevelopers();
        Developer findDeveloper = existingDevelopers.stream()
                                                    .filter(s -> s.getId().equals(id))
                                                    .findFirst().orElse(null);
        findDeveloper.setStatus(Status.DELETED);
        writeDeveloperToFile(existingDevelopers);

    }

    private List<Developer> getAllDevelopers() {
        try(FileInputStream fileInputStream = new FileInputStream(Paths.get("").toAbsolutePath() + pathToFile + FILE_NAME)) {
            String str = new String(fileInputStream.readAllBytes());
            Type type = new TypeToken<List<Developer>>() {}.getType();
            return GSON.fromJson(str, type);
        } catch (IOException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }
    private void writeDeveloperToFile(List<Developer> developers) {
        String jsonCollection = GSON.toJson(developers);
        try (BufferedWriter bw = new BufferedWriter(new FileWriter
                (Paths.get("").toAbsolutePath() + pathToFile + FILE_NAME, false))) {
            bw.write(jsonCollection);
            bw.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Long autoIncrementId(List<Developer> developers) {
        Developer maxIdDeveloper = developers.stream().max(Comparator.comparing(Developer::getId)).orElse(null);
        return Objects.nonNull(maxIdDeveloper) ? maxIdDeveloper.getId() + 1 : 1;
    }
}
