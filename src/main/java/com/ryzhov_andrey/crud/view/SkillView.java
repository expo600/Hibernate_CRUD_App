package com.ryzhov_andrey.crud.view;


import com.ryzhov_andrey.crud.controller.SkillController;
import com.ryzhov_andrey.crud.model.Skill;
import com.ryzhov_andrey.crud.model.Specialty;
import com.ryzhov_andrey.crud.model.Status;
import java.util.List;
import java.util.Scanner;

public class SkillView extends BaseView {

    private final String createMenuMessage = "Select action on skill:\n" +
            " 1. Create\n" +
            " 2. ReadById\n" +
            " 3. Update\n" +
            " 4. Delete\n" +
            " 5. GetAll\n" +
            " 6. Back\n" +
            " 7. Exit";
    private String line = "----------------------------------------------";
    private final Scanner scanner = new Scanner(System.in);
    private final SkillController skillController = new SkillController();


    @Override
    public void create() {
        System.out.println(line);
        System.out.println("Enter skill name: ");
        String name = scanner.next();
        Skill createdSkill = null;
        try {
            createdSkill = skillController.createSkill(name, Status.ACTIVE);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Created skill ...");
        System.out.println("Successful operation");
    }

    @Override
    void readById() {
        System.out.println(line);
        System.out.println("Edit skill\n" + "Enter ID: ");
        Long id = scanner.nextLong();
        Skill readIdSkill = skillController.getSkillById(id);
        System.out.println(" ID |  SKILL  | STATUS \n");
        System.out.println(readIdSkill.toString());
    }

    @Override
    void update() {
        System.out.println(line);
        System.out.println("Edit skill\n" + "Enter ID: ");
        Long id = scanner.nextLong();
        System.out.println("Enter skill name: ");
        String name = scanner.next();
        Skill updatedSkill = skillController.updateSkill(id, name, Status.ACTIVE);
        System.out.println("Updated skill ...");
        System.out.println("Successful operation");
    }

    @Override
    void delete() {
        System.out.println(line);
        System.out.println("Delete skill\n" + "Enter ID: ");
        Long id = scanner.nextLong();
        Skill deleteSkill = skillController.getSkillById(id);
        try {
            skillController.deleteSkill(id);
            System.out.println("Deleted skill ...");
            System.out.println("Successful operation");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Error!");
        }
    }

    @Override
    void print() {
        System.out.println("List of skills: ");
        List<Skill> skills = skillController.getAllSkills();
        System.out.println(" ID |  SKILL  | STATUS  \n");
        skills.forEach((r) -> System.out.println(r.toString()));
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
}
