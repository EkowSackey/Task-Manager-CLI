package main.utils;

import main.models.Priority;
import main.models.Status;

import java.util.Scanner;

public class Input {
    private static final Scanner sc = new Scanner(System.in);

    public static int readInt(String prompt){
        System.out.print(prompt);

        while (!sc.hasNextInt()){
            System.out.println("Invalid input! Please enter a number.");
            sc.nextLine();
        }
        int value = sc.nextInt();
        sc.nextLine();
        return value;
    }

    public static double readDouble(String prompt){
        System.out.print(prompt);

        while (!sc.hasNextDouble()){
            System.out.println("Invalid input! Please enter a decimal number");
            sc.nextLine();
        }

        double value = sc.nextDouble();
        sc.nextLine();
        return value;
    }

    public static String readString(String prompt){
        System.out.print(prompt);
        return sc.nextLine();
    }

    public static Status readStatus(){
        System.out.print("Input status [Must be either 'Completed','Pending' or 'Started']: ");

        String value = sc.nextLine();
        Status status = null;

        switch (value){
            case "Completed": {
                status = Status.COMPLETED;
                break;
            }

            case "Pending": {
                status = Status.PENDING;
                break;
            }

            case "Started": {
                status = Status.STARTED;
                break;
            }

            default:{
                System.out.println("Invalid input! Status set to 'Pending'.");
                status = Status.PENDING;
                break;
            }
        }

        return status;
    }

    public static Priority readPriority(){
        System.out.print("Input priority [Must be either 'Critical, High, Medium, Low']: ");

        String value = sc.nextLine();
        Priority priority = null;

        switch (value){
            case "Critical": {
                priority = Priority.CRITICAL;
                break;
            }
            case "High": {
                priority = Priority.HIGH;
                break;
            }

            case "Medium": {
                priority = Priority.MEDIUM;
                break;
            }

            case "Low": {
                priority = Priority.LOW;
                break;
            }

            default:{
                System.out.println("Invalid input! Priority set to 'Medium'.");
                priority = Priority.MEDIUM;
                break;
            }
        }

        return priority;
    }
}
