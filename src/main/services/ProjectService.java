package main.services;

import main.exceptions.InvalidRangeException;
import main.exceptions.TaskNotFoundException;
import main.models.*;
import main.repository.ProjectList;
import main.utils.Autogen;

import java.util.List;

public class ProjectService {
    public ProjectList projects;

    public ProjectService(ProjectList projects){
        this.projects = projects;
    }

    public void init(){

//      TODO  convert to data pre-loader
    }

    public List<Project> getAllProjects(){

        return projects.getProjects();

    }

    public List<Project> getSoftwareProjects(){

        return projects.getByType("Software Project");

    }

    public List<Project> getHardwareProjects(){

        return projects.getByType("Hardware Project");

    }

    public List<Project> searchByRange(double min, double max){

        if(min >= max){
            throw new InvalidRangeException("Invalid budget range! Min must be lower than max.");
        }
        return projects.getByBudgetRange(min, max);


    }

    public void createSoftwareProject(String name, String description, String type, int teamSize, double budget ){

        SoftwareProject prj = new SoftwareProject(Autogen.addProject(), name, type, description, teamSize, budget);
        projects.addProject(prj);



    }

    public void createHardwareProject(String name, String description, String type, int teamSize, double budget ){

        Project prj = new HardwareProject(Autogen.addProject(), name, type, description, teamSize, budget);
        projects.addProject(prj);



    }

    public Project getProjectById(String prjID){
        return projects.getByID(prjID);
    }

    public Task getTaskById(String id){

        for (Project p: getAllProjects()){
            List<Task> tasks = p.getTasks();

            for (Task t: tasks){
                if (t.getID().equals(id)){
                    return t;
                }
            }
        }

        throw new TaskNotFoundException("Task not found. Try again.");
    }
}
