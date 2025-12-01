package services;

import models.*;
import utils.Autogen;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TaskService {
    static Scanner sc = new Scanner(System.in);
    static ProjectList projects = ProjectService.projects;

    public static void init() {

        System.out.println("*=======================================*");
        System.out.println("||           TASK CATALOG            ||");
        System.out.println("*=======================================*");
        System.out.println("\n");

        System.out.println("_________________*Options*_______________");
        System.out.println("1. View All Tasks");
        System.out.println("2. Create a New  Task");
        System.out.println("3. Update a Task");
        System.out.println("4. Delete a Task");
        System.out.println("0. Back to main menu");


        System.out.println("\n");

        System.out.print("Enter your choice: ");

        if (sc.hasNextInt()) {
            int choice = sc.nextInt();
            sc.nextLine();


            switch (choice) {

                case 1: {
                    viewAllTasks();
                    break;
                }

                case 2: {
                    createTask();
                    break;
                }

                case 3: {
                    System.out.println("Enter VALID a Task ID: ");
                    String task = sc.nextLine();

                    updateTask(task);
                    break;
                }

                case 4: {
                    System.out.println("Enter VALID a Task ID: ");
                    String task = sc.nextLine();
                    deleteTask(task);
                    break;
                }

                case 0: {
                    Ui.init();
                    break;
                }


                default: {
                    System.out.println("Invalid Input! Try again.");
                    init();
                    break;
                }
            }
        } else {
            System.out.println("Please enter a number!");
            sc.nextLine();
            init();
        }

    }

    public static void viewAllTasks() {
        System.out.println("___________*ALL Tasks*______________");
        System.out.println("_____________________________________________________________________________________________________");
        System.out.println("ID     |        TASK NAME         |     ASSIGNED PROJECT ID    |      STATUS       |   PRIORITY     ");
        System.out.println("_____________________________________________________________________________________________________");
        List<Task> allTasks = projects.getAllTasks();
        List<String> ids = new ArrayList<>();

        for (Task t : allTasks) {
            ids.add(t.getID());
            System.out.printf("%s       |", t.getID());
            System.out.printf("  %s            |", t.getName());
            System.out.printf("  %s            |", t.getAssignedProjectID());
            System.out.printf("  %s            |", t.getStatus());
            System.out.printf("  %s            |", t.getPriority());
            System.out.println("\n");


        }


        System.out.println("\nEnter a VALID task ID to update status or priority (0 to go back): ");
        String choice = sc.nextLine();

        if (ids.contains(choice)) {
            updateTask(choice);
        } else if (choice.equals("0")) {
            init();
        } else {
            System.out.println("Invalid Task ID! Try again.");
            viewAllTasks();

        }


    }

    public static void createTask() {
        System.out.println("Enter task name: ");
        String name = sc.nextLine();

        System.out.println("Enter assigned project ID [MUST be a valid Project ID]: ");
        String assigned = sc.nextLine();

        System.out.println("Enter initial  status [MUST be one of {Completed, Pending, Started}]: ");
        String inStatus = sc.nextLine();
        Status status = null;

        switch (inStatus) {
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
            default: {
                System.out.println("Invalid Input! Try again");
                createTask();
            }
        }


        System.out.println("Enter initial priority [MUST be one of {Critical, High, Medium, Low}]: ");
        String inPriority = sc.nextLine();
        Priority priority = null;

        switch (inPriority) {
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

            default: {
                System.out.println("Invalid Input! Try again");
                createTask();
            }
        }


        Task task = new Task(Autogen.addTask(), assigned, name, status, priority);
        Project prj = ProjectService.projects.getByID(assigned);
        prj.addTask(task);

        System.out.println("Task created Successfully! ");
        ProjectService.viewDetails(assigned);
    }

    public static void updateTask(String choice) {
        System.out.printf("__________*Updating  Task: %s*_____________\n", choice);
        Task task = projects.getTaskByID(choice);
        System.out.println(task);
        System.out.println("+==========================================+\n\n");


        System.out.println("Enter new status [MUST be one of {Completed, Pending, Started}]: ");
        String status = sc.nextLine();

        System.out.println("Enter new priority [MUST be one of {Critical, High, Medium, Low}]: ");
        String priority = sc.nextLine();


        switch (status) {
            case "Completed": {
                task.setStatus(Status.COMPLETED);
                break;
            }
            case "Pending": {
                task.setStatus(Status.PENDING);
                break;
            }
            case "Started": {
                task.setStatus(Status.STARTED);
                break;
            }

            default: {
                System.out.println("Invalid status! Try again");
                updateTask(task.getID());
                break;
            }
        }

        switch (priority) {
            case "Critical": {
                task.setPriority(Priority.CRITICAL);
                break;
            }
            case "High": {
                task.setPriority(Priority.HIGH);
                break;
            }
            case "Medium": {
                task.setPriority(Priority.MEDIUM);
                break;
            }
            case "Low": {
                task.setPriority(Priority.LOW);
                break;
            }
            default: {
                System.out.println("Invalid priority! Try again.");
                updateTask(task.getID());
                break;
            }
        }

        ProjectService.viewDetails(task.getAssignedProjectID());


    }

    public static void deleteTask(String task) {
        Task dTask = projects.getTaskByID(task);
        Project p = projects.getByID(dTask.getAssignedProjectID());

        p.deleteTask(dTask);
        System.out.println("Task Deleted!");
        ProjectService.viewDetails(p.getID());
    }

    //public static void viewProjectTasks(String prjID){}
}
