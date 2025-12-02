package services;

import models.Role;
import models.User;

import java.util.Scanner;

public class Ui {





    public static void init(){

        User user = UserService.u;
        String name = user.getUsername();
        Role role = user.getRole();

        String roleStr = String.valueOf(role);

        System.out.println("*=======================================*");
        System.out.println("||     PROJECT MANAGEMENT SYSTEM       ||");
        System.out.println("*=======================================*");
        System.out.println("\n");
        System.out.printf("Current User: %s (%s) ", name, roleStr);
        System.out.println("\n");
        System.out.println("________________*Main Menu*_______________");
        System.out.println("1. Manage Projects");
        System.out.println("2. Manage Tasks");
        System.out.println("3. View Status Reports");
        System.out.println("4. Switch User");
        System.out.println("5. Exit");


        Scanner sc = new Scanner(System.in);

        System.out.print("Enter your choice: ");


        if (sc.hasNextInt()) {
            int choice = sc.nextInt();
            switch (choice) {
                case 1: {
                    ProjectService.init();
                    break;
                }

                case 2: {
                    TaskService.init();
                    break;
                }

                case 3: {
                    ReportService.init();
                    break;
                }

                case 4: {
                    UserService.clearUser();
                    UserService.init();
                    break;
                }

                case 5: {
                    System.out.println("Exiting program...");
                    System.exit(0);
                    break;
                }

                default: {
                    System.out.println("Invalid Input! Try again.");
                    init();
                }
            }
        }else {
            System.out.println("Please input a number.");
            sc.nextLine();
            init();
        }





    }
}
