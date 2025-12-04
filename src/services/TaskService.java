package services;

import exceptions.ProjectNotFoundException;
import models.*;
import utils.Autogen;
import utils.Input;
import utils.Menus;
import utils.Printer;

import java.util.ArrayList;
import java.util.List;


public class TaskService {

    static ProjectList projects = ProjectService.projects;

    public static void init() {

        Printer.printBanner("*TASK CATALOG*");
        Menus.taskCatalog();
    }

    public static void viewAllTasks() {
        Printer.printBanner("*ALL TASKS*");

        List<Task> allTasks = projects.getAllTasks();
        List<String> ids = new ArrayList<>();

        for (Task t : allTasks) {
            ids.add(t.getID());
        }

        Printer.printTaskTable(allTasks);
        Menus.idPicker("task", ids);

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
