package utils;

import exceptions.HowDidYouGetHereException;
import services.*;
import java.util.List;

public class Menus {

    private static String projectID;

    public static void mainMenu(){
        System.out.println("________________*Main Menu*_______________");
        String[] options = {"Manage Projects", "Manage Tasks", "View Status Reports", "Switch User", "Exit"};
        Printer.printOptions(options);
        System.out.println("\n\n");
        int choice = Input.readInt("Enter your choice: ");
        switcher("main", choice );
    }

    public static void projectCatalog(){
        System.out.println("_________________*Options*_______________");
        String[] options = {"View all Projects", "View Software Projects only", "View Hardware Projects only", "Search by Budget", "Create a Software Project", "Create a Hardware Project"};
        Printer.printOptions(options);
        System.out.println("O. Back to Main Menu");
        int choice = Input.readInt("Enter your choice: ");
        switcher("projects", choice);
    }

    public static void taskCatalog(){
        System.out.println("_________________*Options*_______________");
        String[] options = {"View All Tasks", "Create a new Task", "Update a Task", "Delete a Task"};
        Printer.printOptions(options);
        System.out.println("0.Back to Main Menu");
        int choice = Input.readInt("Enter your choice: ");
        switcher("tasks", choice);
    }

    public static void taskSubMenu(String ID){
        projectID = ID;

        System.out.println("_________________*Options*_______________");
        String[] options = {"Add a new Task", "Update a task", "Remove Task"};
        Printer.printOptions(options);
        System.out.println("0. Back to Main Menu");
        int choice = Input.readInt("Enter your choice: ");
        switcher("subtask", choice);
    }

    private static void switcher(String menu, int choice){
        switch (menu){
            case "main":{
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
                        mainMenu();
                        break;
                    }
                }
                break;
            }

            case "projects":{
                switch (choice) {
                    case 1: {
                        ProjectService.viewAllProjects();
                        break;
                    }
                    case 2: {
                        ProjectService.viewSoftwareProjects();
                        break;
                    }
                    case 3: {
                        ProjectService.viewHardwareProjects();
                        break;
                    }
                    case 4: {
                        ProjectService.searchByRange();
                        break;
                    }
                    case 5: {
                        ProjectService.createSoftwareProject();
                        break;
                    }
                    case 6: {
                        ProjectService.createHardwareProject();
                        break;
                    }
                    case 0: {
                        Ui.init();
                        break;
                    }
                    default: {
                        System.out.println("Invalid Input! Try again.");
                        projectCatalog();
                        break;
                    }
                }
                break;
            }

            case "tasks":{
                switch (choice) {

                    case 1: {
                        TaskService.viewAllTasks();
                        break;
                    }
                    case 2: {
                        TaskService.createTask();
                        break;
                    }
                    case 3: {
                        String task =Input.readString("Enter VALID a Task ID: ");
                        TaskService.updateTask(task);
                        break;
                    }
                    case 4: {
                        String task = Input.readString("Enter a Valid Task ID: ");
                        TaskService.deleteTask(task);
                        break;
                    }
                    case 0: {
                        Ui.init();
                        break;
                    }
                    default: {
                        System.out.println("Invalid Input! Try again.");
                        taskCatalog();
                        break;
                    }
                }
                break;
            }

            case "subtask":{
                switch (choice){
                    case 1: {
                        TaskService.createTask();
                        break;
                    }
                    case 2: {
                        String taskID = Input.readString("Enter a VALID task ID to update status and priority: ");
                        TaskService.updateTask(taskID);
                        break;
                    }
                    case 3: {
                        System.out.print("Input a VALID Task ID to delete: ");
                        String taskID = Input.readString("Enter a VALID Task ID to delete: ");
                        TaskService.deleteTask(taskID);
                        break;
                    }
                    case 0: {
                        Ui.init();
                        break;
                    }
                    default:{
                        System.out.println("Invalid choice! Try again.");
                        ProjectService.viewDetails(projectID);
                        break;
                    }
                }
                break;
            }

            default:{
                throw new HowDidYouGetHereException("How exactly did you het here?");
            }


        }

    }

    public static void idPicker(String type, List<String> ids){
        String choice;
        if (type.equals("project")){
            choice = Input.readString("\nEnter a VALID project ID to view details (0 to go back): ");

            if (ids.contains(choice)){
                ProjectService.viewDetails(choice);
            }
            else if (choice.equals("0")) {
                ProjectService.init();
            }
            else {
                System.out.println("Invalid project ID! Try again.");
                ProjectService.viewSoftwareProjects();
            }
        }

        else if (type.equals("task")){
            choice = Input.readString("Enter a Valid Task ID to update status or priority (0 to go back): ");

            if (ids.contains(choice)) {
                TaskService.updateTask(choice);
            } else if (choice.equals("0")) {
                TaskService.init();
            } else {
                System.out.println("Invalid Task ID! Try again.");
                TaskService.viewAllTasks();
            }
        }

    }

}
