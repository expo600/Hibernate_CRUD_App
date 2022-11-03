package com.ryzhov_andrey.crud.view;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ryzhov_andrey.crud.controller.DeveloperController;
import com.ryzhov_andrey.crud.controller.SkillController;
import com.ryzhov_andrey.crud.controller.SpecialtyController;
import com.ryzhov_andrey.crud.model.Developer;
import com.ryzhov_andrey.crud.model.Skill;
import com.ryzhov_andrey.crud.model.Specialty;
import com.ryzhov_andrey.crud.model.Status;

import java.util.ArrayList;
import java.util.List;
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
    private final SkillController skillController = new SkillController();
    private final SpecialtyController specialtyController = new SpecialtyController();
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    @Override
    void create() {
        System.out.println(line);
        System.out.println("Enter developer firstname: ");
        String firstName = scanner.next();
        System.out.println("Enter developer lastname: ");
        String lastName = scanner.next();
        System.out.println("Enter developer skills: ");
        List<Skill> skills = addSkills();
        System.out.println("Enter developer specialty: ");
        Specialty specialty = addSpecialty();
        Developer createdDeveloper = developerController.createDeveloper(firstName, lastName,
                skills, specialty, Status.ACTIVE);
        System.out.println("Created developer: \n" + GSON.toJson(createdDeveloper));

    }

    @Override
    void edit() {
        System.out.println(line);
        System.out.println("Edit developer\n" + "Enter ID: ");
        Long id = scanner.nextLong();
        System.out.println("Enter developer firstname: ");
        String firstname = scanner.next();
        System.out.println("Enter developer lastname: ");
        String lastName = scanner.next();
        System.out.println("Enter developer skills: ");
        List<Skill> skills = addSkills();
        System.out.println("Enter developer specialty: ");
        Specialty specialty = addSpecialty();
        Developer deleteDeveloper = developerController.updateDeveloper(id, firstname, lastName, skills,
                specialty, Status.ACTIVE);
        System.out.println("Successful operation");
        System.out.println("Edit developer: \n" + GSON.toJson(deleteDeveloper));
    }

    @Override
    void delete() {
        System.out.println(line);
        System.out.println("Delete developer \n" + "Enter ID: ");
        Long id = scanner.nextLong();
        Developer deletedDeveloper = developerController.getDeveloperById(id);
        try {
            developerController.deleteDeveloper(id);
            System.out.println("Successful operation");
            System.out.println("Deleted developer: \n" + GSON.toJson(deletedDeveloper));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Error!");
        }
    }

    @Override
    void print() {
        System.out.println("List of developers: ");
        List<Developer> developers = developerController.getAllDevelopers();
        System.out.println(GSON.toJson(developers));
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

    private List<Skill> addSkills() {
        List<Skill> result = new ArrayList<>();
        System.out.println("Select skill ids: ");
        List<Skill> currentSkills = skillController.getAllSkills();
        System.out.println(currentSkills);
        long choice = scanner.nextLong();

        while (choice != -1) {
            Long finalChoice = choice;
            currentSkills.stream().filter(s -> s.getId().equals(finalChoice)).findFirst().ifPresent(result::add);
            choice = scanner.nextLong();
        }

        return result;
    }

    private Specialty addSpecialty() {
        Specialty specialty = new Specialty();
        System.out.println("Select specialty ids: ");
        List<Specialty> currentSpecialties = specialtyController.getAllSpecialties();
        System.out.println(currentSpecialties);
        long choice = scanner.nextLong();
        while (choice != -1) {
            Long finalChoice = choice;
            specialty = currentSpecialties.stream().filter(s -> s.getId()
                                          .equals(finalChoice)).findFirst().orElse(null);
            choice = scanner.nextLong();
        }
        return specialty;
    }
}