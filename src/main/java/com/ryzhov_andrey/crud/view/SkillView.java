package com.ryzhov_andrey.crud.view;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ryzhov_andrey.crud.controller.SkillController;
import com.ryzhov_andrey.crud.model.Skill;
import com.ryzhov_andrey.crud.model.Specialty;
import com.ryzhov_andrey.crud.model.Status;

import java.util.List;
import java.util.Scanner;

public class SkillView extends BaseView {

    private final String createMenuMessage = "Select action on skill:\n" +
            " 1. Create\n" +
            " 2. Update\n" +
            " 3. Delete\n" +
            " 4. Print\n" +
            " 5. Exit";
    private String line = "----------------------------------------------";
    private final Scanner scanner = new Scanner(System.in);
    private final SkillController skillController = new SkillController();
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();


    @Override
    public void create()  {
        System.out.println(line);
        System.out.println("Enter skill name: ");
        String name = scanner.next();
        Skill createdSkill = null;
        try {
            createdSkill = skillController.createSkill(name,Status.ACTIVE);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Created skill: \n" + GSON.toJson(createdSkill));
    }

    @Override
    void update() {
        System.out.println(line);
        System.out.println("Edit skill\n" + "Enter ID: ");
        Long id = scanner.nextLong();
        System.out.println("Enter skill name: ");
        String name = scanner.next();
        Skill updatedSkill = skillController.updateSkill(id, name,Status.ACTIVE);
        System.out.println("Successful operation");
        System.out.println("Updated skill: \n" + GSON.toJson(updatedSkill));
    }

    @Override
    void delete() {
        System.out.println(line);
        System.out.println("Delete skill\n" + "Enter ID: " );
        Long id = scanner.nextLong();
      Skill deleteSkill = skillController.getSkillById(id);
        try {
            skillController.deleteSkill(id);
            System.out.println("Successful operation");
            System.out.println("Deleted skill: \n" + GSON.toJson(deleteSkill));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Error!");
        }
    }

    @Override
    void print() {
        System.out.println("List of skills: ");
        List<Skill> skills = skillController.getAllSkills();
        System.out.println(GSON.toJson(skills));
    }

    @Override
    void show() {
        boolean isExit = false;
        while (true) {
            System.out.println(line);
            System.out.println(createMenuMessage);
            System.out.println(line);
            Integer choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    create();
                    break;
                case 2:
                    update();
                    break;
                case 3:
                    delete();
                    break;
                case 4:
                    print();
                    break;
                case 5:
                    isExit = true;
                    break;
                default:
                    System.out.println("Invalid input. Please try again!");
                    break;
            }

            if (isExit)
                break;
        }
        scanner.close();
    }

}
