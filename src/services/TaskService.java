package services;

import exceptions.ProjectNotFoundException;
import exceptions.TaskNotFoundException;
import models.*;
import utils.Autogen;

import java.util.List;


public class TaskService {

    private final ProjectList projects;
    private final ProjectService projectService;

    public TaskService(ProjectList projects, ProjectService projectService){
        this.projects = projects;
        this.projectService = projectService;
    }

    public void init() {

//        TODO
    }

    public List<Task> getAllTasks() {

        return projects.getAllTasks();

    }

    public void createTask(String name, String assigned, Status status, Priority priority) {

            try {
                Project prj = projects.getByID(assigned);
                Task task = new Task(Autogen.addTask(), assigned, name, status, priority);
                prj.addTask(task);


            } catch (ProjectNotFoundException e) {

                throw new ProjectNotFoundException("Project with the ID you assigned does not exist. Try again.");
            }
    }


    public void updateTask(Task task, Status newStatus, Priority newPriority) {

        task.setStatus(newStatus);
        task.setPriority(newPriority);

    }

    public void deleteTask(String taskId) throws Exception {
        try{
            Task dTask = projects.getTaskByID(taskId);
            Project p = projects.getByID(dTask.getAssignedProjectID());
            p.deleteTask(dTask);


        }catch (TaskNotFoundException | ProjectNotFoundException e){
            throw new Exception(e.getMessage());
        }

    }

}
