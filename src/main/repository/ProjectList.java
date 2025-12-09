package main.repository;

import main.exceptions.ProjectNotFoundException;
import main.exceptions.TaskNotFoundException;
import main.models.Priority;
import main.models.Project;
import main.models.Task;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ProjectList {
    private final List<Project> projects = new ArrayList<Project>();

    public void addProject(Project project){
        projects.add(project);
    }

    public List<Project> getProjects(){
        return projects;
    }



    public int getSize(){
        return projects.size();
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

    public void addTask(Task task, String projectId){
        Project prj = getByID(projectId);

        prj.addTask(task);
    }


    public List<Task> getTasks(String projectID){
        // get all tasks associated with a project
        Project prj = getByID(projectID);
        List<Task> t = prj.getTasks();
        if (t.isEmpty()){
            System.out.println("No tasks in Project");
            return null;
        }
        return t;

    }

    public List<Task> getAllTasks(){
        // get all tasks
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

    public List<Task> getTasksByPriority(Priority priority){
        List<Task> prtasks = new ArrayList<>();
        for (Project p : projects){
            List<Task> tasks = p.getTasks();

            for (Task t : tasks){
                if (t.getPriority() == priority){
                    prtasks.add(t);
                }
            }
        }

        return prtasks;
    }





}
