package services;

import models.*;
import utils.Autogen;
import utils.Input;
import utils.Menus;
import utils.Printer;

import java.util.ArrayList;
import java.util.List;

public class ProjectService {
    public static final ProjectList projects = new ProjectList();



    public static void init(){

        Printer.printBanner("*PROJECT CATALOG*");
        Menus.projectCatalog();
    }

    public static void viewAllProjects(){
        Printer.printBanner("*ALL PROJECTS*");
        List<Project> allProjects = projects.getProjects();
        projects.getSize();
        List<String> ids = new ArrayList<>();

        for (Project p: allProjects){
            ids.add(p.getID());
        }
        Printer.printProjectTable(allProjects);
        Menus.idPicker("project", ids);
    }

    public static void viewSoftwareProjects(){
        Printer.printBanner("*SOFTWARE PROJECTS*");
        List<Project> swProjects = projects.getByType("Software Project");
        List<String> ids = new ArrayList<>();

        for (Project p: swProjects){
            ids.add(p.getID());
        }

        Printer.printProjectTable(swProjects);

        Menus.idPicker("project", ids);

    }

    public static void viewHardwareProjects(){
        Printer.printBanner("*HARDWARE PROJECTS*");

        List<Project> hwProjects = projects.getByType("Hardware Project");
        List<String> ids = new ArrayList<>();

        for (Project p: hwProjects){
            ids.add(p.getID());
        }

        Printer.printProjectTable(hwProjects);


        Menus.idPicker("project", ids);


    }

    public static void searchByRange(){
        System.out.println("Input a VALID range");

        double min = Input.readDouble("Min: [Must be a decimal eg. 3500.00]");
        double max = Input.readDouble("Max: [Must be a decimal eg. 3500.00]");


        if(min >= max){
            System.out.println("Invalid range!");
            searchByRange();
        }
        List<Project> budgetprjs = projects.getByBudgetRange(min, max);

        Printer.printBanner(String.format("*Projects in range $%.2f and $%.2f*", min, max) );

        List<String> ids = new ArrayList<>();

        for (Project p: budgetprjs){
            ids.add(p.getID());
        }

        Printer.printProjectTable(budgetprjs);

        Menus.idPicker("project", ids);

    }

    public static void createSoftwareProject(){
        Printer.printBanner("Creating a Software project");

        String name = Input.readString("Enter a name: ");
        System.out.print("Enter a short description: ");
        String description = Input.readString("Enter a short description: ");
        int teamSize = Input.readInt("Enter a team size: ");

        double budget = Input.readDouble("Enter a budget (eg. 3500.00): ");

        String type = "Software Project";

        SoftwareProject prj = new SoftwareProject(Autogen.addProject(), name, type, description, teamSize, budget);
        projects.addProject(prj);

        System.out.println("Software Project Created Successfully!");
        viewAllProjects();

    }

    public static void createHardwareProject(){
        Printer.printBanner("Creating a Hardware project");

        String name = Input.readString("Enter a name: ");
        String description = Input.readString("Enter a short description: ");

        int teamSize = Input.readInt("Enter a team size: ");
        double budget = Input.readDouble("Enter a budget: ");

        String type = "Hardware Project";

        Project prj = new HardwareProject(Autogen.addProject(), name, type, description, teamSize, budget);
        projects.addProject(prj);

        System.out.println("Hardware Project Created Successfully!");
        viewAllProjects();

    }

    public static void viewDetails(String prjID){
        Project prj = projects.getByID(prjID);

        Printer.printBanner(String.format("*PROJECT DETAILS: %S*", prjID));

        System.out.printf("Project Name: %s\n", prj.getName());
        System.out.printf("Type: %s\n", prj.getType());
        System.out.printf("Team Size: %d\n", prj.getTeamSize());
        System.out.printf("Budget: $%.2f\n\n", prj.getBudget());
        System.out.printf("Completion: %s\n", prj.getCompletion());

        List<Task> tasks = prj.getTasks();

        System.out.println("Associated Tasks:");
        Printer.printTaskTable(tasks);
        System.out.println("\n");

        Menus.taskSubMenu(prjID);
    }
}
