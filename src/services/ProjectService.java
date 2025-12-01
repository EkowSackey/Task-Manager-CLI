package services;

import models.*;
import utils.Autogen;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ProjectService {
    public static final ProjectList projects = new ProjectList();
    static int numberOfProjects = projects.getSize();
    static Scanner sc = new Scanner(System.in);

    public static void init(){

        System.out.println("*=======================================*");
        System.out.println("||           PROJECT CATALOG            ||");
        System.out.println("*=======================================*");
        System.out.println("\n");

        System.out.println("_________________*Options*_______________");
        System.out.printf("1. View All Projects (%d) \n", numberOfProjects);
        System.out.println("2. Software Projects Only");
        System.out.println("3. Hardware Projects Only");
        System.out.println("4. Search by Budget Range");
        System.out.println("5. Create a New Software Project");
        System.out.println("6. Create a New Hardware Project");
        System.out.println("0. Back to main menu");



        System.out.println("\n");

        System.out.print("Enter your choice: ");


        int choice = sc.nextInt();
        sc.nextLine();


        switch (choice){

            case 1: {
                viewAllProjects();
                break;
            }

            case 2: {
                viewSoftwareProjects();
                break;
            }

            case 3: {
                viewHardwareProjects();
                break;
            }

            case 4: {
                searchByRange();
                break;
            }

            case 5: {
                createSoftwareProject();
                break;
            }
            case 6: {
                createHardwareProject();
            }

            case 0: {
                Ui.init();
            }

            default: {System.out.println("Invalid Input! Try again.");}
        }

    }

    public static void viewAllProjects(){
        System.out.println("___________*ALL Projects*______________");
        System.out.println("___________________________________________________________________________________________________________________________________");
        System.out.println("ID     |        PROJECT NAME          |           TYPE            |            DESCRIPTION           | TEAM SIZE  |   BUDGET     ");
        System.out.println("___________________________________________________________________________________________________________________________________");
        List<Project> allProjects = projects.getProjects();
        List<String> ids = new ArrayList<>();

        for (Project p: allProjects){
            ids.add(p.getID());
            System.out.printf("%s       |",p.getID());
            System.out.printf("  %s     |", p.getName());
            System.out.printf("  %s     |", p.getDescription());
            System.out.printf("  %s     |", p.getType());
            System.out.printf("  %s     |", p.getDescription());
            System.out.printf("  %d     |", p.getTeamSize());
            System.out.printf("  %.2f   |", p.getBudget());
            System.out.println("\n");

        }



        System.out.println("\nEnter a VALID project ID to view details (0 to go back): ");
        String choice = sc.nextLine();

        if (ids.contains(choice)){
            viewDetails(choice);
        }
        else if (choice.equals("0")) {
            init();

        }else  {
            System.out.println("Invalid project ID! Try again.");

        }


    }

    public static void viewSoftwareProjects(){
        System.out.println("___________*Software Projects*______________");
        System.out.println("_____________________________________________________________________________________________________________________________________");
        System.out.println("ID          |       PROJECT NAME       |      DESCRIPTION     |       TYPE     | TEAM SIZE  |   BUDGET     ");
        System.out.println("_____________________________________________________________________________________________________________________________________");
        List<Project> swProjects = projects.getByType("Software Project");
        List<String> ids = new ArrayList<>();

        for (Project p: swProjects){
            ids.add(p.getID());
            System.out.printf("%s       |",p.getID());
            System.out.printf("  %s     |", p.getName());
            System.out.printf("  %s     |", p.getDescription());
            System.out.printf("  %s     |", p.getType());
            System.out.printf("  %s     |", p.getDescription());
            System.out.printf("  %d     |", p.getTeamSize());
            System.out.printf("  %.2f   |", p.getBudget());

        }



        System.out.println("\nEnter a VALID project ID to view details (0 to go back): ");
        String choice = sc.nextLine();

        if (ids.contains(choice)){
            viewDetails(choice);
        }
        else if (choice.equals("0")) {
            init();
        }
        else {
            System.out.println("Invalid project ID! Try again.");

        }

    }

    public static void viewHardwareProjects(){
        System.out.println("___________*Hardware Projects*______________");
        System.out.println("_____________________________________________________________________________________________________________________________________");
        System.out.println("ID          |       PROJECT NAME       |      DESCRIPTION     |       TYPE     | TEAM SIZE  |   BUDGET     ");
        System.out.println("_____________________________________________________________________________________________________________________________________");
        List<Project> swProjects = projects.getByType("Hardware Project");
        List<String> ids = new ArrayList<>();

        for (Project p: swProjects){
            ids.add(p.getID());
            System.out.printf("%s       |",p.getID());
            System.out.printf("  %s     |", p.getName());
            System.out.printf("  %s     |", p.getDescription());
            System.out.printf("  %s     |", p.getType());
            System.out.printf("  %s     |", p.getDescription());
            System.out.printf("  %d     |", p.getTeamSize());
            System.out.printf("  %.2f   |", p.getBudget());

        }



        System.out.println("\nEnter a VALID project ID to view details (0 to go back): ");
        String choice = sc.nextLine();

        if (ids.contains(choice)){
            viewDetails(choice);
        }
        else if (choice.equals("0")) {
            init();
        }
        else {
            System.out.println("Invalid project ID! Try again.");

        }


    }

    public static void searchByRange(){
        System.out.println("Input a VALID range");
        System.out.print("Min: ");
        double min = sc.nextDouble();
        sc.nextLine();

        System.out.print("Max: ");
        double max = sc.nextDouble();
        sc.nextLine();

        if(min >= max){
            System.out.println("Invalid range!");
            return;
        }
        List<Project> budgetprjs = projects.getByBudgetRange(min, max);

        System.out.printf("___________*Projects in range $%.2f and $%.2f *______________\n", min, max);
        System.out.println("_____________________________________________________________________________________________________________________________________");
        System.out.println("ID          |       PROJECT NAME       |      DESCRIPTION     |       TYPE     | TEAM SIZE  |   BUDGET     ");
        System.out.println("_____________________________________________________________________________________________________________________________________");
        List<String> ids = new ArrayList<>();

        for (Project p: budgetprjs){
            ids.add(p.getID());
            System.out.printf("%s       |",p.getID());
            System.out.printf("  %s     |", p.getName());
            System.out.printf("  %s     |", p.getDescription());
            System.out.printf("  %s     |", p.getType());
            System.out.printf("  %s     |", p.getDescription());
            System.out.printf("  %d     |", p.getTeamSize());
            System.out.printf("  %.2f   |", p.getBudget());
            System.out.println("\n");

        }



        System.out.println("\nEnter a VALID project ID to view details (0 to go back): ");
        String choice = sc.nextLine();

        if (ids.contains(choice)){
            viewDetails(choice);
        }

        else if (choice.equals("0")) {
            init();
        }
        else {
            System.out.println("Invalid project ID! Try again.");

        }

    }

    public static void createSoftwareProject(){
        System.out.println("__________*Creating a Software project*_____________\n");
        System.out.print("Enter a name: ");
        String name = sc.nextLine();
        System.out.print("Enter a short description: ");
        String description = sc.nextLine();
        System.out.print("Enter a team size: ");
        int teamSize = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter a budget (eg. 3500.00): ");
        double budget = sc.nextDouble();
        sc.nextLine();
        String type = "Software Project";

        SoftwareProject prj = new SoftwareProject(Autogen.addProject(), name, type, description, teamSize, budget);
        projects.addProject(prj);

        System.out.println("Software Project Created Successfully!");
        viewAllProjects();




    }

    public static void createHardwareProject(){
        System.out.println("__________*Creating a Hardware project*_____________\n");
        System.out.print("Enter a name: ");
        String name = sc.nextLine();
        System.out.print("Enter a short description: ");
        String description = sc.nextLine();
        System.out.print("Enter a team size: ");
        int teamSize = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter a budget (eg. 3500.00): ");
        double budget = sc.nextDouble();
        sc.nextLine();
        String type = "Hardware Project";

        Project prj = new HardwareProject(Autogen.addProject(), name, type, description, teamSize, budget);
        projects.addProject(prj);

        System.out.println("Hardware Project Created Successfully!");
        viewAllProjects();

    }

    public static void viewDetails(String prjID){
        Project prj = projects.getByID(prjID);

        System.out.println("*=======================================*");
        System.out.printf("||         PROJECT DETAILS: %s      ||\n", prjID);
        System.out.println("*=======================================*");
        System.out.println("\n");

        System.out.printf("Project Name: %s\n", prj.getName());
        System.out.printf("Type: %s\n", prj.getType());
        System.out.printf("Team Size: %d\n", prj.getTeamSize());
        System.out.printf("Budget: $%.2f\n\n", prj.getBudget());
        System.out.printf("Completion: %s\n", prj.getCompletion());

        List<Task> tasks = prj.getTasks();

        System.out.println("Associated Tasks:");
        System.out.println("________________________________________________________________________");
        System.out.println("ID    |     Task Name       |     Status       |      Priority          ");
        System.out.println("________________________________________________________________________");

        for (Task t: tasks){
            System.out.printf("%s  |   %s           |    %s            |    %s         \n", t.getID(), t.getName(), t.getStatus(), t.getPriority());
        }

        System.out.println("Options: ");
        System.out.println("1. Add a new Task ");
        System.out.println("2. Update  Task Status ");
        System.out.println("3. Remove  Task  ");
        System.out.println("4. Back to main menu ");

        System.out.print("Enter a choice: ");
        int choice = sc.nextInt();
        sc.nextLine();



        switch (choice){
            case 1: {
                TaskService.createTask();
                break;
            }

            case 2: {
                System.out.print("Input a VALID task ID to update: ");
                String taskID = sc.nextLine();

                TaskService.updateTask(taskID);
                break;
            }

            case 3: {
                System.out.print("Input a VALID Task ID to delete: ");
                String taskID = sc.nextLine();

                TaskService.deleteTask(taskID);
                break;
            }

            case 4: {
                Ui.init();
                break;
            }
        }

    }




}
