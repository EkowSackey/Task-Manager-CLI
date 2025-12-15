package main.repository;

import main.exceptions.InvalidRangeException;
import main.exceptions.ProjectNotFoundException;
import main.exceptions.TaskNotFoundException;
import main.models.Priority;
import main.models.Project;
import main.models.Task;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * This class uses a List as a data storage for projects.
 * <p>This class currently represents a repository of "database level" methods for data storage, retrieval and deletion
 * controlled by the Project Service</p>
 * <p>addProject adds a project to the list.</p>
 * <p>getProjects returns a list of all projects in the list.</p>
 * <p>getByID finds a project by a given ID and returns the Project.</p>
 * <p>getByID finds a project by a given ID and returns the Project.</p>
 * <p>getByType finds projects of a given type and returns a list of Projects</p>
 * <p>getAllTasks returns all the tasks in all the projects in the list.</p>
 * <p>getTaskByID returns a task in the list by searching the projects for a matching ID.</p>
 */
public class ProjectList {
    private final List<Project> projects = new ArrayList<Project>();

    public synchronized void addProject(Project project){
        projects.add(project);
    }

    public synchronized List<Project> getProjects(){
        return List.copyOf(projects);
    }

    public synchronized Project getByID(String ID){

        for (Project p: projects){
            String id= p.getID();
            if (id.equals(ID)){
                return p;
            }
        }
        throw new ProjectNotFoundException("Project with ID " + ID + " not found!");
    }

    public synchronized List<Project> getByType(String type){
        List<Project> projectsOfType = new ArrayList<>();

        projects.stream()
                .filter(p-> p.getType().equals(type))
                .forEach(projectsOfType::add);

        return projectsOfType;
    }

    public synchronized List<Project> getByBudgetRange(double min, double max){
        List<Project> filtered = new ArrayList<Project>();

        if (min > max) throw new InvalidRangeException("Invalid range");

        projects.stream()
                .filter(x-> x.getBudget() >= min && x.getBudget() <=max)
                .forEach(filtered::add);

        if (filtered.isEmpty()) {
            return Collections.emptyList();
        }
        return filtered;
    }

    public synchronized List<Task> getAllTasks(){

        List<Task> allTasks = new ArrayList<>();

        for (Project p : projects){
            allTasks.addAll(p.getTasks());
        }
        return allTasks;
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
