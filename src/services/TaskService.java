package services;

import exceptions.ProjectNotFoundException;
import models.*;
import utils.Autogen;
import utils.Input;
import utils.Printer;

import java.util.ArrayList;
import java.util.List;


public class TaskService {

    static ProjectList projects = ProjectService.projects;

    public static void init() {

        Printer.printBanner("*TASK CATALOG*");

        System.out.println("_________________*Options*_______________");
        System.out.println("1. View All Tasks");
        System.out.println("2. Create a New  Task");
        System.out.println("3. Update a Task");
        System.out.println("4. Delete a Task");
        System.out.println("0. Back to main menu");
        System.out.println("\n");

        int choice = Input.readInt("Enter your choice: " );



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
                String task =Input.readString("Enter VALID a Task ID: ");
                updateTask(task);
                break;
            }
            case 4: {
                String task = Input.readString("Enter a Valid Task ID: ");
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


    }

    public static void viewAllTasks() {
        Printer.printBanner("*ALL TASKS*");

        List<Task> allTasks = projects.getAllTasks();
        List<String> ids = new ArrayList<>();

        for (Task t : allTasks) {
            ids.add(t.getID());
        }

        Printer.printTaskTable(allTasks);

        String choice = Input.readString("Enter a Valid Task ID to update status or priority (0 to go back): ");

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

        while (true){
            String name = Input.readString("Enter a task name: ");
            String assigned = Input.readString("Enter assigned project ID [Must be a valid Project ID]: ");
            Status status = Input.readStatus();
            Priority priority = Input.readPriority();

            Task task = new Task(Autogen.addTask(), assigned, name, status, priority);

            try {
                Project prj = ProjectService.projects.getByID(assigned);
                prj.addTask(task);
                System.out.println("Task created Successfully! ");
                ProjectService.viewDetails(assigned);
                break;
            } catch (ProjectNotFoundException e) {
                System.out.println(e.getMessage());
                System.out.println("Please restart with a valid project ID\n\n");
            }

        }
    }

    public static void updateTask(String choice) {

        Printer.printBanner(String.format("*Updating  Task: %s*", choice));
        Task task = projects.getTaskByID(choice);
        System.out.println(task);

        Status status = Input.readStatus();
        Priority priority = Input.readPriority();

        task.setPriority(priority);
        task.setStatus(status);

        ProjectService.viewDetails(task.getAssignedProjectID());

    }

    public static void deleteTask(String task) {
        Task dTask = projects.getTaskByID(task);
        Project p = projects.getByID(dTask.getAssignedProjectID());

        p.deleteTask(dTask);
        System.out.println("Task Deleted!");
        ProjectService.viewDetails(p.getID());
    }


}
