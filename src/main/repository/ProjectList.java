package main.repository;

import main.exceptions.InvalidRangeException;
import main.exceptions.ProjectNotFoundException;
import main.exceptions.TaskNotFoundException;
import main.models.Project;
import main.models.Task;

import java.util.*;


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

    private final HashMap<String, Project> prjs = new HashMap<>();

    public synchronized void addProject(Project project){
//        projects.add(project);
        prjs.put(project.getID(), project);
    }

    public synchronized List<Project> getProjects(){
        return List.copyOf(prjs.values());

    }

    public synchronized Project getByID(String ID){
        Project p = prjs.get(ID);

        if (p != null){
            return p;
        }
        throw new ProjectNotFoundException("Project with ID " + ID + " not found!");
    }

    public synchronized List<Project> getByType(String type){
        return prjs.values().stream()
                .filter(p->p.getType().equals(type))
                .toList();
    }

    public synchronized List<Project> getByBudgetRange(double min, double max){

        if (min > max) throw new InvalidRangeException("Invalid range");

        return prjs.values().stream()
                .filter(x-> x.getBudget() >= min && x.getBudget() <=max)
                .toList();

    }

    public synchronized List<Task> getAllTasks(){
        return prjs.values().stream()
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
