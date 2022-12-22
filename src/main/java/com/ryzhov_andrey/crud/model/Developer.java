package com.ryzhov_andrey.crud.model;

import lombok.Data;

import java.util.List;

@Data
public class Developer {

    private Long id;
    private String firstname;
    private String lastName;
    private List<Skill> skills;
    private Specialty specialty;
    private Status status;


    public Developer(Long id, String firstName, String lastName,
                     List<Skill> skills, Specialty specialty) {
        this.id = id;
        this.firstname = firstName;
        this.lastName = lastName;
        this.skills = skills;
        this.specialty = specialty;
    }

    public Developer(Long id, String firstName, String lastName,
                     List<Skill> skills, Specialty specialty, Status status) {
        this.id = id;
        this.firstname = firstName;
        this.lastName = lastName;
        this.skills = skills;
        this.specialty = specialty;
        this.status = status;
    }

    @Override
    public String toString() {

        StringBuilder postBuilder = new StringBuilder();

        for (Skill skill : skills) {
            postBuilder.append(skill.getName()).append(" | ");
        }

        return "  " + id + " | " + firstname + " | " + lastName +
                " | " + postBuilder + " | " + specialty.getName() + " | " + status;

    }
}

