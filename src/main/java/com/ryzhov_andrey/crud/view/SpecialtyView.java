package com.ryzhov_andrey.crud.view;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ryzhov_andrey.crud.controller.SpecialtyController;
import com.ryzhov_andrey.crud.model.Specialty;
import com.ryzhov_andrey.crud.model.Status;
import java.util.List;
import java.util.Scanner;

public class SpecialtyView extends BaseView {

    private final String createMenuMessage = "Select action on specialty:\n" +
            " 1. Create\n" +
            " 2. Edit\n" +
            " 3. Delete\n" +
            " 4. Print\n" +
            " 5. Exit";
    private String line = "----------------------------------------------";
    private final Scanner scanner = new Scanner(System.in);
    private final SpecialtyController specialtyController = new SpecialtyController();
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    @Override
    public void create() {
        System.out.println(line);
        System.out.println("Enter specialty name: ");
        String name = scanner.next();
        Specialty createdSpecialty = null;
        try {
            createdSpecialty = specialtyController.createSpecialty(name,Status.ACTIVE);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Created specialty: \n" + GSON.toJson(createdSpecialty));
    }

    @Override
    void edit() {
        System.out.println(line);
        System.out.println("Edit specialty\n" + "Enter ID: ");
        Long id = scanner.nextLong();
        System.out.println("Enter specialty name: ");
        String name = scanner.next();
        Specialty updateSpecialty = specialtyController.updateSpecialty(id, name,Status.ACTIVE);
        System.out.println("Successful operation");
        System.out.println("Updated skill: \n" + GSON.toJson(updateSpecialty));
    }

    @Override
    void delete() {
        System.out.println(line);
        System.out.println("Delete specialty\n" + "Enter ID: ");
        Long id = scanner.nextLong();
        Specialty deleteSpecialty = specialtyController.getSpecialtyById(id);
        try {
            specialtyController.deleteSpecialty(id);
            System.out.println("Successful operation");
            System.out.println("Deleted skill: \n" + GSON.toJson(deleteSpecialty));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Error!");
        }
    }


    @Override
    void print() {
        System.out.println("List of specialties: ");
        List<Specialty> specialties = specialtyController.getAllSpecialties();
        System.out.println(GSON.toJson(specialties));
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
                    edit();
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