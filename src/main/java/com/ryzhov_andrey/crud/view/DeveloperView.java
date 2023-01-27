package com.ryzhov_andrey.crud.view;


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
            " 2. ReadById\n" +
            " 3. Update\n" +
            " 4. Delete\n" +
            " 5. GetAll\n" +
            " 6. Back\n" +
            " 7. Exit";
    private String line = "----------------------------------------------";
    private final Scanner scanner = new Scanner(System.in);
    private final DeveloperController developerController = new DeveloperController();
    private final SkillController skillController = new SkillController();
    private final SpecialtyController specialtyController = new SpecialtyController();


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
        System.out.println("Created developer ...");

    }

    @Override
    void readById() {
        System.out.println(line);
        System.out.println("Edit specialty\n" + "Enter ID: ");
        Long id = scanner.nextLong();
        Developer developerById = developerController.getDeveloperById(id);
        System.out.println(" ID |  NAME  |  LASTNAME  |   SKILL   |  SPECIALTY | STATUS \n");
        System.out.println(developerById);
    }

    @Override
    void update() {
        System.out.println(line);
        System.out.println("Edit developer\n" + "Enter ID: ");
        Long id = scanner.nextLong();
        System.out.println("Enter developer firstname: ");
        String firstName = scanner.next();
        System.out.println("Enter developer lastname: ");
        String lastName = scanner.next();
        System.out.println("Enter developer skills: ");
        List<Skill> skills = addSkills();
        System.out.println("Enter developer specialty: ");
        Specialty specialty = addSpecialty();
        Developer deleteDeveloper = developerController.updateDeveloper(id, firstName, lastName, skills,
                specialty, Status.ACTIVE);
        System.out.println("Update developer ...");
        System.out.println("Successful operation");
    }

    @Override
    void delete() {
        System.out.println(line);
        System.out.println("Delete developer \n" + "Enter ID: ");
        Long id = scanner.nextLong();
        Developer deletedDeveloper = developerController.getDeveloperById(id);
        try {
            developerController.deleteDeveloper(id);
            System.out.println("Deleted developer ...");
            System.out.println("Successful operation");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Error!");
        }
    }

    @Override
    void print() {
        System.out.println("List of developers: ");
        List<Developer> developers = developerController.getAllDevelopers();
        System.out.println(" ID |  NAME  |  LASTNAME  |   SKILL   |  SPECIALTY | STATUS \n");

        developers.forEach((w) -> System.out.println((w.toString())));
    }

    @Override
    void backToBeginning() {
        new MainView().run();
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
                    readById();
                    break;
                case 3:
                    update();
                    break;
                case 4:
                    delete();
                    break;
                case 5:
                    print();
                    break;
                case 6:
                    backToBeginning();
                case 7:
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
        specialty = currentSpecialties.stream().filter(s -> s.getId()
                .equals(choice)).findFirst().orElse(null);

        return specialty;
    }
}