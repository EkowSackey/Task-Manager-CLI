package main.services;

import main.models.Priority;
import main.models.Status;
import main.repository.ProjectList;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class ConcurrencyService {

    static void main(String[] args) {

        ProjectList projects = new ProjectList();
        ProjectService ps = new ProjectService(projects);
        TaskService ts = new TaskService(projects);

        class TaskUpdater implements Runnable{

            private String taskId;
            private Status status;
            private Priority priority;

            public TaskUpdater(String taskId, Status status, Priority priority){
                this.taskId = taskId;
                this.priority = priority;
                this.status = status;
            }



            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + " updating " + taskId + "-> Status: " + status + " Priority: "+ priority);
                try {
                    Thread.sleep(5000);
                    ts.getById(taskId).setStatusAndPriority(status, priority);

                } catch (InterruptedException e) {
                    System.out.println("they didn't let me sleep");

                }
            }
        }

        ps.createSoftwareProject("alpha", "desc", "s", 4, 140);
        ts.createTask("task1", "P001", Status.PENDING, Priority.MEDIUM);
        ts.createTask("task2", "P001", Status.PENDING, Priority.HIGH);
        ts.createTask("task3", "P001", Status.PENDING, Priority.CRITICAL);

        ExecutorService executor = Executors.newFixedThreadPool(2);

        Runnable updater1 = new TaskUpdater("T001", Status.STARTED, Priority.LOW);

        Runnable updater2 = new TaskUpdater("T002", Status.COMPLETED, Priority.LOW);

        Runnable updater3 = new TaskUpdater("T003", Status.COMPLETED, Priority.LOW);

        System.out.println("Starting 3 threads...");
        executor.execute(updater1);
        executor.execute(updater2);
        executor.execute(updater3);



        executor.shutdown();
//        executor.shutdownNow();

        while (!executor.isTerminated()){

        }

        if ((!ts.getById("T001").getStatus().equals(Status.STARTED))) throw new AssertionError();
        if ((!ts.getById("T002").getStatus().equals(Status.COMPLETED))) throw new AssertionError();
        if ((!ts.getById("T003").getStatus().equals(Status.COMPLETED))) throw new AssertionError();

        else
            System.out.println("All tasks updated safely and concurrently");
    }

}
