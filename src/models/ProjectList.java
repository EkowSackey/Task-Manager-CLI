package models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ProjectList {

    private final Project[] pjs = new Project[5];
    private int idx = 0;

    public void addProject(Project project){
        pjs[idx] = project;
        idx++;
    }

    public List<Project> getProjects(){
        List<Project> p = new ArrayList<>();
        Collections.addAll(p, pjs);
        return p;
    }

    public int getSize(){
       int count = 0;
       for (Project p: pjs){
           if (p != null) count++;
       }
       return count;
    }

    public Project getByID(String ID){

        for (Project p: pjs){
            if (p!=null){
                String id = p.getID();
                if (id.equals(ID)) {

                    return p;

                }
            }

        }
        System.out.printf("Project with ID %d not found!", ID);
        return null;

    }

    public List<Project> getByType(String type){
        List<Project> projectsOfType = new ArrayList<>();
        for (Project p: pjs){
            if (p!=null) {
                String ptype = p.getType();
                if (ptype.equals(type)) {
                    projectsOfType.add(p);
                }
            }
        }
        return projectsOfType;
    }

    public List<Project> getByBudgetRange(double min, double max){
        List<Project> filtered = new ArrayList<Project>();

        if (min > max) System.out.println("Invalid Range!");

        for(Project p: pjs){
            if (p!=null) {
                double budget = p.getBudget();
                if (budget >= min && budget <= max)
                    filtered.add(p);
            }
        }

        if (filtered.isEmpty()) {
            System.out.println("No Projects Found");
            return null;
        }
        return filtered;
    }


    public List<Task> getAllTasks(){
        // get all tasks
        List<Task> allTasks = new ArrayList<>();

        for (Project p : pjs){
            if (p!=null) {
                allTasks.addAll(p.getTasks());
            }
        }
        return allTasks;
    }

    public Task getTaskByID(String taskID){
        List<Task> allTasks = getAllTasks();

        for (Task t: allTasks){
            if (t!=null && t.getID().equals(taskID)){
                return t;
            }
        }

        return null;
    }

}
