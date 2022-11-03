package com.ryzhov_andrey.crud.view;

import java.util.Scanner;

public class MainView {
    private final SkillView skillView = new SkillView();
    private final DeveloperView developerView = new DeveloperView();
    private final SpecialtyView specialtyView = new SpecialtyView();
    private final Scanner scanner = new Scanner(System.in);
    private String line = "----------------------------------------------";

    public void run() {
        System.out.println(line);
        System.out.println("Choose an action: \n" +
                "   1. Skill.\n" +
                "   2. Specialty.\n" +
                "   3. Developer.\n" +
                "   4. Exit ");
        System.out.println(line);
        Integer choice = scanner.nextInt();

        switch (choice) {
            case 1:
                skillView.show();
                break;
            case 2:
                specialtyView.show();
                break;
            case 3:
                developerView.show();
                break;
            case 4:
                break;
            default:
                System.out.println("Invalid input. Please try again!");
                break;
        }
        scanner.close();
    }
}
