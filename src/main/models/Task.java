package main.models;

public class Task {
    private final String ID;
    private final String assignedProjectID;
    private final String name;
    private Status status;
    private Priority priority;

    public Task(String ID, String assignedProjectID, String name, Status status, Priority priority) {
        this.ID = ID;
        this.assignedProjectID = assignedProjectID;
        this.name = name;
        this.status = status;
        this.priority = priority;
    }

//    getters

    public String getID() {
        return ID;
    }

    public String getAssignedProjectID() {
        return assignedProjectID;
    }

    public Priority getPriority() {
        return priority;
    }

    public Status getStatus() {
        return status;
    }

    public String getName() {
        return name;
    }


//    setters

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public void setStatus(Status status) {
        this.status = status;
    }


    @Override
    public String toString() {
        return String.format(
                "Task ID: %s\nAssigned Project: Project ID %s\nTask Name: %s\nTask Status: %s\nPriority: %s ",
                ID, assignedProjectID, name, status, priority
        );
    }




}
