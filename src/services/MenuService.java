package services;

import exceptions.InvalidRangeException;
import exceptions.ProjectNotFoundException;
import exceptions.TaskNotFoundException;
import models.Priority;
import models.Project;
import models.Status;
import models.Task;
import utils.Input;
import utils.Menus;
import utils.Printer;

import java.util.ArrayList;
import java.util.List;

public final class MenuService {

    public enum MenuType { MAIN, PROJECTS, TASKS, SUBTASK }

    private final UserService userService;
    private final ProjectService projectService;
    private final TaskService taskService;


    private MenuType current = MenuType.MAIN;
    private boolean running = true;
    private String currentProjectId;

    public MenuService(UserService u, ProjectService p, TaskService t) {
        this.userService = u;
        this.projectService = p;
        this.taskService = t;
    }

    public void run() {
        while (running) {
            render();
            int choice = Input.readInt("Enter your choice: ");
            dispatch(choice);
        }
        System.out.println("Exiting program...");
    }

    private void render() {
        switch (current) {
            case MAIN     -> {

                Printer.printBanner("PROJECT MANAGEMENT SYSTEM");
                System.out.printf("Current User: %s (%s)%n%n ", userService.u.getUsername(), userService.u.getRole());
                Menus.renderMainMenu();
            }
            case PROJECTS -> Menus.renderProjectCatalog();
            case TASKS    -> Menus.renderTaskCatalog();
            case SUBTASK  -> Menus.renderTaskSubMenu(currentProjectId);
        }
    }

    private void dispatch(int choice) {
        switch (current) {
            case MAIN     -> handleMain(choice);
            case PROJECTS -> handleProjects(choice);
            case TASKS    -> handleTasks(choice);
            case SUBTASK  -> handleSubtask(choice);
        }
    }

    private void handleMain(int choice) {

        switch (choice) {
            case 1 -> current = MenuType.PROJECTS;
            case 2 -> current = MenuType.TASKS;
            case 3 -> showStatusReports();
            case 4 -> switchUser();
            case 5 -> running = false;
            default -> Printer.printError("Invalid Input! Try again.");
        }
    }

    private void handleProjects(int choice) {

            switch (choice) {
                case 1 -> {
                    var projects = projectService.getAllProjects();
                    renderProjects(projects);
                    pickProjectDetails(projects);
                }
                case 2 -> {
                    List<Project> projects;
                    try {
                        projects = projectService.getSoftwareProjects();
                        renderProjects(projects);
                        pickProjectDetails(projects);
                    } catch (ProjectNotFoundException e) {
                        System.out.println(e.getMessage());
                        System.out.println("Try again");

                    }

                }
                case 3 -> {
                    var projects = projectService.getHardwareProjects();

                    renderProjects(projects);
                    pickProjectDetails(projects);
                }
                case 4 -> {
                    double min = Input.readDouble("Min budget: ");
                    double max = Input.readDouble("Max budget: ");
                    var projects = projectService.searchByRange(min, max);
                    Printer.printBanner(String.format("*Projects in range $%.2f and $%.2f*", min, max));
                    renderProjects(projects);
                    pickProjectDetails(projects);
                }
                case 5 -> createSoftwareProject();
                case 6 -> createHardwareProject();
                case 0 -> current = MenuType.MAIN;
                default -> Printer.printError("Invalid Input! Try again.");
            }

    }

    private void handleTasks(int choice) {
        switch (choice) {
            case 1 -> {
                var tasks = taskService.getAllTasks();
                Printer.printBanner("*ALL TASKS*");
                renderTasks(tasks);
                pickTaskForUpdate(tasks);
            }
            case 2 -> createTask();
            case 3 -> {
                String id = Input.readString("Enter a VALID Task ID: ");
                updateTask(id);
            }
            case 4 -> {
                String id = Input.readString("Enter a VALID Task ID to delete: ");
                deleteTask(id);
            }
            case 0 -> current = MenuType.MAIN;
            default -> Printer.printError("Invalid Input! Try again.");
        }
    }

    private void handleSubtask(int choice) {
        switch (choice) {
            case 1 -> createTask();
            case 2 -> {
                String id = Input.readString("Enter a VALID Task ID to update: ");
                updateTask(id);
            }
            case 3 -> {
                String id = Input.readString("Enter a VALID Task ID to delete: ");
                deleteTask(id);
            }
            case 0 -> current = MenuType.MAIN;
            default -> {
                Printer.printError("Invalid choice! Try again.");
                showProjectDetails(currentProjectId);
            }
        }
    }

    /* ===== UI rendering helpers ===== */

    private void renderProjects(List<Project> projects) {
        Printer.printProjectTable(projects);
    }

    private void pickProjectDetails(List<Project> projects) {
        List<String> ids = new ArrayList<>();

        for (Project p: projects){
            ids.add(p.getID());
        }
        String choice = Input.readString("\nEnter a valid Project ID to view details (0 to go back): ").trim();

         if (choice.equals("0")) {
            current = MenuType.PROJECTS;
            return;
        }
         if (!ids.contains(choice)){
             Printer.printError("Invalid project ID! Try again.");
             current = MenuType.PROJECTS;
             return;
        }
        currentProjectId = choice;
         showProjectDetails(choice);
         current = MenuType.SUBTASK;


    }

    private void showProjectDetails(String id) {

        Project prj;
        try {
            prj = projectService.getProjectById(id);
        } catch (ProjectNotFoundException e) {
            Printer.printError(e.getMessage());
            current = MenuType.PROJECTS;
            return;
        }

        Printer.printBanner(String.format("*PROJECT DETAILS: %S*", id));

        System.out.printf("Project Name: %s%n", prj.getName());
        System.out.printf("Type: %s%n", prj.getType());
        System.out.printf("Team Size: %d%n", prj.getTeamSize());
        System.out.printf("Budget: $%.2f%n%n", prj.getBudget());
        System.out.printf("Completion: %s%n", prj.getCompletion());

        List<Task> tasks = prj.getTasks();

        System.out.println("Associated Tasks:");
        Printer.printTaskTable(tasks);
        System.out.println("\n");

    }

    private void renderTasks(List<Task> tasks) {
        Printer.printTaskTable(tasks);
    }

    private void pickTaskForUpdate(List<Task> tasks) {
        List<String> ids = new ArrayList<>();

        for (Task t: tasks){
            ids.add(t.getID());
        }
        String choice = Input.readString("\nEnter a valid Task Id to update (0 to go back) : ");
        if (ids.contains(choice)){
            updateTask(choice);
        } else if (choice.equals("0")) {
            current = MenuType.TASKS;
            return;

        }
        else {
            Printer.printError("Invalid Task ID. Try again");
        }

    }

    private void createSoftwareProject() {
        Printer.printBanner("Creating a Software project");
        // collect inputs
        String name = Input.readString("Project name: ");
        String description = Input.readString("Enter a short description: ");
        int teamSize = Input.readInt("Enter a team size: ");
        int budget = Input.readInt("Budget: ");
        String type = "Software Project";

        projectService.createSoftwareProject(name, description, type, teamSize, budget);
        Printer.printSuccess("Software Project created successfully!");
        current = MenuType.PROJECTS;
    }

    private void createHardwareProject() {
        Printer.printBanner("Creating a Hardware project");
        // collect inputs
        String name = Input.readString("Project name: ");
        String description = Input.readString("Enter a short description: ");
        int teamSize = Input.readInt("Enter a team size: ");
        int budget = Input.readInt("Budget: ");
        String type = "Hardware Project";

        projectService.createHardwareProject(name, description, type, teamSize, budget);
        Printer.printSuccess("Hardware Project created successfully!");
        current = MenuType.PROJECTS;

    }

    private void createTask() {
        String name = Input.readString("Task name: ");
        String projectId = Input.readString("Assign a valid Project ID: ");
        Status status = Input.readStatus();
        Priority priority  = Input.readPriority();


        try {
            taskService.createTask(name, projectId, status, priority);
            Printer.printSuccess("Task Created Successfully!");
        } catch (ProjectNotFoundException e) {
            Printer.printError(e.getMessage());

        }
        current = MenuType.TASKS;
    }

    private void updateTask(String taskId) {
        Printer.printBanner(String.format("*Updating  Task: %s*", taskId));
        Task task;

        try {
            task = projectService.getTaskById(taskId);
        } catch (TaskNotFoundException e) {
            Printer.printError(e.getMessage());
            return;

        }

        Status newStatus = Input.readStatus();
        Priority newPriority = Input.readPriority();


        taskService.updateTask(task, newStatus, newPriority);
        Printer.printSuccess("Task Updated Successfully!");


    }

    private void deleteTask(String taskId) {
        String confirm = Input.readString("Delete task " + taskId + "?(y/n)").trim().toLowerCase();
        if (!confirm.startsWith("y")) return;

        try {
            taskService.deleteTask(taskId);
            Printer.printSuccess("Task deleted 🕊️");
        } catch (Exception e){
            Printer.printError(e.getMessage());
        }
    }

    private void showStatusReports() {
        Printer.printBanner("*PROJECT STATUS*");
        List<Project> allProjects = projectService.getAllProjects();
        Printer.printReport(allProjects);
    }

    private void switchUser() {
        userService.clearUser();
        userService.login();
    }
}
