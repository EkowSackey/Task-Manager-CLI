package main.utils;

import main.models.Priority;
import main.models.Status;

import java.util.Scanner;
import java.util.regex.Pattern;

public class Input {

    private static final Pattern STATUS_PATTERN = Pattern.compile("^(completed|pending|started)$", Pattern.CASE_INSENSITIVE);
    private static final Pattern PRIORITY_PATTERN = Pattern.compile("^(critical|high|medium|low)$", Pattern.CASE_INSENSITIVE);
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
        while (true){
            System.out.print("Input status [Must be either 'Completed','Pending' or 'Started']: ");
            String value = sc.nextLine().trim();

            if (STATUS_PATTERN.matcher(value).matches()){
                return Status.valueOf(value.toUpperCase());
            }

            System.out.println("Invalid input. Please type Completed, Pending or Started.");
        }

    }

    public static Priority readPriority(){
        while(true){
            System.out.print("Input priority [Must be either 'Critical, High, Medium, Low']: ");
            String value = sc.nextLine().trim();

            if (PRIORITY_PATTERN.matcher(value).matches()){
                return Priority.valueOf(value.toUpperCase());
            }
            System.out.println("Invalid input. Please type Critical, High, Medium or Low");
        }
    }
}

