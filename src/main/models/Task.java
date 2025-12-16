package main.models;

import com.google.gson.annotations.SerializedName;
import main.interfaces.Completable;

import java.util.concurrent.atomic.AtomicReference;

public class Task implements Completable {
    private final String ID;
    private final String assignedProjectID;
    private final String name;
    private static final class State{
        final Status status;
        final Priority priority;
        State(Status status, Priority priority){
            this.status = status;
            this.priority= priority;
        }
    }
    private transient AtomicReference<State> state;

    @SerializedName("status")
    private Status statusField;

    @SerializedName("priority")
    private Priority priorityField;

    public Task(String ID, String assignedProjectID, String name, Status status, Priority priority) {
        this.ID = ID;
        this.assignedProjectID = assignedProjectID;
        this.name = name;
        State st = new State(status, priority);
        this.state = new AtomicReference<>(st);
        this.statusField = st.status;
        this.priorityField = st.priority;

    }

//    getters

    public String getID() {
        return ID;
    }

    public String getAssignedProjectID() {
        return assignedProjectID;
    }

    public Priority getPriority() {
        return state.get().priority;
    }

    public Status getStatus() {
        return state.get().status;
    }

    public String getName() {
        return name;
    }


//    setters

    public void setPriority(Priority priority) {
        state.updateAndGet(s-> new State(s.status, priority));
        this.priorityField = priority;
    }

    public void setStatus(Status status) {
        state.updateAndGet(s-> new State(status,s.priority));
        this.statusField = status;
    }

    public void setStatusAndPriority(Status status, Priority priority){
        state.set(new State(status, priority));
        this.statusField = status;
        this.priorityField = priority;
    }


    @Override
    public String toString() {
        State s = state.get();
        return String.format(
                "Task ID: %s\nAssigned Project: Project ID %s\nTask Name: %s\nTask Status: %s\nPriority: %s ",
                ID, assignedProjectID, name, s.status, s.priority
        );
    }


    @Override
    public void markComplete() {
        setStatus(Status.COMPLETED);
    }
}
