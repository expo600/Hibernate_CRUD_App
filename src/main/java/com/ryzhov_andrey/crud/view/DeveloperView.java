package com.ryzhov_andrey.crud.view;

import com.ryzhov_andrey.crud.controller.DeveloperController;
import com.ryzhov_andrey.crud.model.Developer;
import com.ryzhov_andrey.crud.model.Skill;
import com.ryzhov_andrey.crud.model.Specialty;
import com.ryzhov_andrey.crud.model.Status;

import java.util.ArrayList;
import java.util.Scanner;

public class DeveloperView extends BaseView {

    private final String createMenuMessage = "Select action on developer:\n" +
            " 1. Create\n" +
            " 2. Edit\n" +
            " 3. Delete\n" +
            " 4. Print\n" +
            " 5. Exit";
    private String line = "----------------------------------------------";
    private final Scanner scanner = new Scanner(System.in);
    private final DeveloperController developerController = new DeveloperController();


    @Override
    void create() {
        System.out.println("Enter developer firstName: ");
        String name = scanner.nextLine();
        System.out.println("Enter developer lastName: ");
        String lastName = scanner.nextLine();
        System.out.println("Enter developer skills: ");
        String skills = scanner.nextLine();
        System.out.println("Enter developer specialty: ");
        String specialty = scanner.nextLine();
        Developer createdDeveloper = null;
        try {
            createdDeveloper = developerController.createDeveloper(name, lastName,
                    new ArrayList<Skill>(), new Specialty(null, specialty));
        } catch (Exception e) {
            e.printStackTrace();
        }
        createdDeveloper.setStatus(Status.ACTIVE);
        System.out.println("Created developer: " + createdDeveloper);

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