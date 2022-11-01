package com.ryzhov_andrey.crud.repository.gson;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ryzhov_andrey.crud.model.Specialty;
import com.ryzhov_andrey.crud.repository.SpecialtyRepository;

import java.io.*;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class GsonSpecialtyRepositoryImp implements SpecialtyRepository {

    private final static String pathToFile = "\\src\\main\\resources\\";
    private final static String FILE_NAME = "specialties.json";
    private final Gson GSON = new Gson();

    @Override
    public Specialty getById(Long id) {
        return getAllSpecialties().stream().filter(s -> s.getId().equals(id)).findFirst().orElse(null);
    }

    @Override
    public List<Specialty> getAll() {
        return getAllSpecialties();
    }

    @Override
    public Specialty save(Specialty specialty) {
        List<Specialty> existingSpecialties = getAllSpecialties();
        specialty.setId(autoIncrementId(existingSpecialties));
        existingSpecialties.add(specialty);
        writeSpecialtyToFile(existingSpecialties);
        return specialty;
    }

    @Override
    public Specialty update(Specialty specialty) {
        List<Specialty> existingSpecialties = getAllSpecialties();
        existingSpecialties.forEach(existingSpecialty -> {
            if(existingSpecialty.getId().equals(specialty.getId())) {
                existingSpecialty.setName(specialty.getName());
            }
        });
        writeSpecialtyToFile(existingSpecialties);
        return specialty;
    }

    @Override
    public void deleteById(Long id) {
        List<Specialty> existingSpecialties = getAllSpecialties();
        existingSpecialties.removeIf(s -> s.getId().equals(id));
        writeSpecialtyToFile(existingSpecialties);
    }

    private List<Specialty> getAllSpecialties() {
        try {
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(
                            new FileInputStream(Paths.get("").toAbsolutePath() + pathToFile + FILE_NAME),
                            StandardCharsets.UTF_8));
            String str = reader
                    .lines()
                    .collect(Collectors.joining(System.lineSeparator()));

            Type type = new TypeToken<List<Specialty>>() {}.getType();
            return GSON.fromJson(str, type);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }
    private void writeSpecialtyToFile(List<Specialty> specialties) {
        String jsonCollection = new Gson().toJson(specialties);
        try (BufferedWriter bw = new BufferedWriter(new FileWriter
                (Paths.get("").toAbsolutePath() + pathToFile + FILE_NAME, false))) {
            bw.write(jsonCollection);
            bw.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Long autoIncrementId(List<Specialty> specialties) {
        Specialty maxIdSpecialty = specialties.stream().max(Comparator.comparing(Specialty::getId)).orElse(null);
        return Objects.nonNull(maxIdSpecialty) ? maxIdSpecialty.getId() + 1 : 1;
    }

}
