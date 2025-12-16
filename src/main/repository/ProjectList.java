package main.repository;

import main.exceptions.InvalidRangeException;
import main.exceptions.ProjectNotFoundException;
import main.exceptions.TaskNotFoundException;
import main.models.Project;
import main.models.Task;
import main.utils.FileUtils;
import main.utils.Printer;

import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.util.*;


/**
 * This class uses a hashMap as a data storage for projects.
 * <p>This class currently represents a repository of "database level" methods for data storage, retrieval and deletion
 * controlled by the Project Service</p>
 * <p>addProject adds a project to the map.</p>
 * <p>getProjects returns a list of all projects in the list.</p>
 * <p>getByID finds a project by a given ID and returns the Project.</p>
 * <p>getByID finds a project by a given ID and returns the Project.</p>
 * <p>getByType finds projects of a given type and returns a list of Projects</p>
 * <p>getAllTasks returns all the tasks in all the projects in the list.</p>
 * <p>getTaskByID returns a task in the list by searching the projects for a matching ID.</p>
 */
public class ProjectList {

    private final HashMap<String, Project> projects = new HashMap<>();
    private final Path path = Path.of("src","main","data", "projects_data.json");

    public void init() throws IOException {

            Printer.printSuccess("Loading projects from file...");
            int count = 0;
            List<Project> swProjects = FileUtils.loadswProjects(path);
            List<Project> hwProjects = FileUtils.loadhwProjects(path);
            for (Project p : swProjects){
                addProject(p);
                count++;
            }
            for (Project p: hwProjects){
                addProject(p);
                count++;
            }

            Printer.printSuccess(count + " projects loaded successfully from projects_data.json");

    }

    public void exit() throws IOException {
        Printer.printSuccess("Saving project data...");
        List<Project> prjs = getProjects();
        FileUtils.saveProjects(path, prjs);
        Printer.printSuccess("✅Data written to " + path + " successfully");
    }

    public synchronized void addProject(Project project){

        projects.put(project.getID(), project);
    }

    public synchronized List<Project> getProjects(){
        return List.copyOf(projects.values());

    }

    public synchronized Project getByID(String ID){
        Project p = projects.get(ID);

        if (p != null){
            return p;
        }
        throw new ProjectNotFoundException("Project with ID " + ID + " not found!");
    }

    public synchronized List<Project> getByType(String type){
        return projects.values().stream()
                .filter(p->p.getType().equals(type))
                .toList();
    }

    public synchronized List<Project> getByBudgetRange(double min, double max){

        if (min > max) throw new InvalidRangeException("Invalid range");

        return projects.values().stream()
                .filter(x-> x.getBudget() >= min && x.getBudget() <=max)
                .toList();

    }

    public synchronized List<Task> getAllTasks(){
        return projects.values().stream()
                .flatMap(p-> p.getTasks().stream())
                .toList();
    }

    public synchronized Task getTaskByID(String taskID){
        List<Task> allTasks = getAllTasks();

        for (Task t: allTasks){
            if (t.getID().equals(taskID)){
                return t;
            }
        }
        throw new TaskNotFoundException("Task with ID " + taskID + " does not exist!");
    }

}
