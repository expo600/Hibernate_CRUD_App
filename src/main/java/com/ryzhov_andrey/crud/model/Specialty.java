package com.ryzhov_andrey.crud.model;

import lombok.Data;

@Data
public class Specialty {

    private Long id;
    private String name;
    private Status status;


    public Specialty(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Specialty( String name) {
        this.name = name;
    }

    public Specialty(Long id, String name, Status status) {
        this.id = id;
        this.name = name;
        this.status = status;
    }

    public Specialty() {
    }

    @Override
    public String toString() {
        return "  " + id + " | " + name + " | " + status;
    }


}
