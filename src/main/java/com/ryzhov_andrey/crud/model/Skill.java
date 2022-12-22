package com.ryzhov_andrey.crud.model;

import lombok.Data;

@Data
public class Skill {

    private Long id;
    private String name;
    private Status status;

    public Skill(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Skill( String name) {
        this.name = name;
    }

    public Skill(Long id, String name, Status status) {
        this.id = id;
        this.name = name;
        this.status = status;
    }

    public Skill() {

    }

    @Override
    public String toString() {
        return "  " + id + " | " + name + " | " + status;
    }
}
