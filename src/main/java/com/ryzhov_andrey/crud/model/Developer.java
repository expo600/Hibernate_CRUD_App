package com.ryzhov_andrey.crud.model;

import lombok.Data;

import java.util.List;

@Data
public class Developer{

    private Long id;
    private String name;
    private String lastName;
    private List<Skill> skills;
    private Specialty specialty;
    private Status status;


    public Developer(Long id, String name, String lastName,
                     List<Skill> skills, Specialty specialty) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.skills = skills;
        this.specialty = specialty;
    }
    public Developer(Long id, String name, String lastName,
                     List<Skill> skills, Specialty specialty, Status status) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.skills = skills;
        this.specialty = specialty;
        this.status = status;
    }
}

