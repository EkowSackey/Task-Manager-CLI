package main.repository;

import main.exceptions.ProjectNotFoundException;
import main.exceptions.TaskNotFoundException;
import main.models.Priority;
import main.models.Project;
import main.models.Task;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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

    public void addProject(Project project){
        projects.add(project);
    }

    public List<Project> getProjects(){
        return projects;
    }

    public Project getByID(String ID){

        for (Project p: projects){
            String id= p.getID();
            if (id.equals(ID)){
                return p;
            }
        }
        throw new ProjectNotFoundException("Project with ID " + ID + " not found!");
    }

    public List<Project> getByType(String type){
        List<Project> projectsOfType = new ArrayList<>();
        for (Project p: projects){
            String ptype = p.getType();
            if (ptype.equals(type)){
                projectsOfType.add(p);
            }
        }
        return projectsOfType;
    }

    public List<Project> getByBudgetRange(double min, double max){
        List<Project> filtered = new ArrayList<Project>();

        if (min > max) System.out.println("Invalid Range!");

        for(Project p: projects){
            double budget = p.getBudget();
            if (budget >= min && budget <=max)
                filtered.add(p);
        }

        if (filtered.isEmpty()) {
            System.out.println("No Projects Found");
            return Collections.emptyList();
        }
        return filtered;
    }

    public List<Task> getAllTasks(){

        List<Task> allTasks = new ArrayList<>();

        for (Project p : projects){
            allTasks.addAll(p.getTasks());
        }
        return allTasks;
    }

    public Task getTaskByID(String taskID){
        List<Task> allTasks = getAllTasks();

        for (Task t: allTasks){
            if (t.getID().equals(taskID)){
                return t;
            }
        }
        throw new TaskNotFoundException("Task with ID " + taskID + " does not exist!");
    }

}
