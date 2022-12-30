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
            " 2. Update\n" +
            " 3. Delete\n" +
            " 4. GetAll\n" +
            " 5. Exit";
    private String line = "----------------------------------------------";
    private final Scanner scanner = new Scanner(System.in);
    private final SpecialtyController specialtyController = new SpecialtyController();

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
        System.out.println("Created specialty ...");
        System.out.println("Successful operation");
    }

    @Override
    void update() {
        System.out.println(line);
        System.out.println("Update specialty\n" + "Enter ID: ");
        Long id = scanner.nextLong();
        System.out.println("Enter specialty name: ");
        String name = scanner.next();
        Specialty updateSpecialty = specialtyController.updateSpecialty(id, name,Status.ACTIVE);
        System.out.println("Updated specialty ...");
        System.out.println("Successful operation");
    }

    @Override
    void delete() {
        System.out.println(line);
        System.out.println("Delete specialty\n" + "Enter ID: ");
        Long id = scanner.nextLong();
        Specialty deleteSpecialty = specialtyController.getSpecialtyById(id);
        try {
            specialtyController.deleteSpecialty(id);
            System.out.println("Deleted specialty ...");
            System.out.println("Successful operation");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Error!");
        }
    }


    @Override
    void print() {
        System.out.println("List of specialties: ");
        List<Specialty> specialties = specialtyController.getAllSpecialties();
        System.out.println(" ID | SPECIALTY | STATUS | \n");

        specialties.forEach((r) -> System.out.println(r.toString()));
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