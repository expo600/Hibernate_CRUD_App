package com.ryzhov_andrey.crud.model;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "developers")
public class Developer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "developer_skills",
            joinColumns = {@JoinColumn(name = "developer_id")},
            inverseJoinColumns = {@JoinColumn(name = "skill_id")})
    private List<Skill> skills;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "specialty_id")
    private Specialty specialty;
    @Column(name = "status_name")
    @Enumerated(EnumType.STRING)
    private Status status;

    public Developer() {
    }

    public Developer(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Developer(String firstName, String lastName, List<Skill> skills, Specialty specialty, Status status) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.skills = skills;
        this.specialty = specialty;
        this.status = status;
    }

    public Developer(Long id, String firstName, String lastName,
                     List<Skill> skills, Specialty specialty) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.skills = skills;
        this.specialty = specialty;
    }

    public Developer(Long id, String firstName, String lastName,
                     List<Skill> skills, Specialty specialty, Status status) {
        this.id = id;
        this.firstName = firstName;
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

        return "  " + id + " | " + firstName + " | " + lastName +
                " | " + postBuilder  + specialty.getName() + " | " + status;

    }
}

