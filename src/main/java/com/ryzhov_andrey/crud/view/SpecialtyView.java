package com.ryzhov_andrey.crud.view;

import com.ryzhov_andrey.crud.controller.SpecialtyController;
import com.ryzhov_andrey.crud.model.Specialty;
import com.ryzhov_andrey.crud.model.Status;

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

    @Override
    public void create() {
        System.out.println(line);
        System.out.println(createMenuMessage);
        System.out.println("Enter skill name: ");
        String name = scanner.nextLine();
        Specialty createdSpecialty = null;
        try {
            createdSpecialty = specialtyController.createSpecialty(name);
        } catch (Exception e) {
            e.printStackTrace();
        }
        createdSpecialty.setStatus(Status.ACTIVE);
        System.out.println("Created specialty: " + createdSpecialty);
    }

    @Override
    void edit() {

    }

    @Override
    void delete() {

    }

    @Override
    void print() {

    }

    @Override
    void show() {
        boolean isExit = false;
        while (true) {
            print();
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