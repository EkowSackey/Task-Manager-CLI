package services;

import java.util.Scanner;

public class Ui {

    public static void init(){
//        TODO: implement user logic
        String user = "Ekow ";
        String role = "ADMIN";
        System.out.println("*=======================================*");
        System.out.println("||     PROJECT MANAGEMENT SYSTEM       ||");
        System.out.println("*=======================================*");
        System.out.println("\n");
        System.out.printf("Current User: %s (%s) ", user, role);
        System.out.println("\n");
        System.out.println("________________*Main Menu*_______________");
        System.out.println("1. Manage Projects");
        System.out.println("2. Manage Tasks");
        System.out.println("3. View Status Reports");
        System.out.println("4. Switch User");
        System.out.println("5. Exit");


        Scanner sc = new Scanner(System.in);

        System.out.print("Enter your choice: ");
        int choice = sc.nextInt();


        switch (choice){
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

            default: {System.out.println("Invalid Input! Try again.");}
        }





    }
}
