package com.ryzhov_andrey.crud.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "skills")
public class Skill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "skill_name")
    private String name;
    @Column(name = "status_name")
    @Enumerated(EnumType.STRING)
    private Status status;

    public Skill() {
    }

    public Skill(String name) {
        this.name = name;
    }

    public Skill(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Skill(Long id, String name, Status status) {
        this.id = id;
        this.name = name;
        this.status = status;
    }

    @Override
    public String toString() {
        return "  " + id + " | " + name + " | " + status;
    }
}
