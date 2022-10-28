package com.ryzhov_andrey.crud;

import com.ryzhov_andrey.crud.model.Skill;
import com.ryzhov_andrey.crud.repository.gson.GsonSkillRepositoryImpl;

import java.io.IOException;

public class Application {
    public static void main(String[] args) throws IOException {
        GsonSkillRepositoryImpl gsonSkillRepository = new GsonSkillRepositoryImpl();
        // gsonSkillRepository.getAll();
        //gsonSkillRepository.getById(2);
        //gsonSkillRepository.deleteById(4);
        // gsonSkillRepository.save(new Skill(4,"D"));
       // gsonSkillRepository.update(new Skill(2,"L"));

    }
}
