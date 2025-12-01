package models;

import java.util.ArrayList;
import java.util.List;

public abstract class Project {
    private String ID;
    private String name;
    private String type;
    private String description;
    private int teamSize;
    private double budget;
    private final List<Task> tasks = new ArrayList<Task>();
    private double completion;

    public Project(String ID, String name, String type, String description, int teamSize, double budget){
        this.ID = ID;
        this.name = name;
        this.type = type;
        this.description = description;
        this.teamSize = teamSize;
        this.budget = budget;
    }



//    getters
    public String getID() {return this.ID;}
    public  String getName(){return this.name;}
    public  String getType(){return this.type;}
    public  String getDescription() {return this.description;}
    public  int getTeamSize(){ return this.teamSize;}
    public  double getBudget(){return this.budget;}

//    setters
    public void setName(String name){this.name = name;};
//    public void setID(String ID){this.ID = ID;};
    public void setType(String type){this.type = type;};
    public void setDescription(String description){this.description = description;};
    public void setTeamSize(int teamSize){this.teamSize = teamSize;};
    public void setBudget(double budget){this.budget = budget;};

    public List<Task> getByStatus(Status status){
        List<Task> t = new ArrayList<Task>();

        for(Task task: tasks){
            Status stat = task.getStatus();
            if (stat == status){
                t.add(task);
            }
        }
        return t;
    }

//display methods-- for test
    public void displayProject(){
        System.out.println("Project ID: "+ ID);
        System.out.println("Project Name: " + name);
        System.out.println("Project Type: "+ type);
        System.out.println("Description: " + description);
        System.out.println("Team Size: " + teamSize);
        System.out.println("Budget: "+ budget);
    }




//    task methods
    public void addTask(Task task){
        tasks.add(task);
    }

    public void deleteTask(Task task){
        tasks.remove(task);
    }

    public List<Task> getTasks(){
        return tasks;
    }

    public String getCompletion(){
        int number = tasks.size();
        int completed = getByStatus(Status.COMPLETED).size();

        double percentage = (number > 0) ? ((double) completed / number) * 100 : 0.00;

        return String.format("Project %s is %.2f %% COMPLETED\n\n", name, percentage);
    }




    @Override
    public String toString() {
        return String.format(
                "Project ID: %s\nProject Name: %s\nProject Type: %s\nDescription: %s\nTeam Size: %d\nBudget: %.2f",
                ID, name, type, description, teamSize, budget
        );
    }





}
