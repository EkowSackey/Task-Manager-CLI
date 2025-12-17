package main.services;

import main.models.Priority;
import main.models.Status;
import main.repository.ProjectList;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class ConcurrencyService {

    private final ProjectService projectService;
    private final TaskService taskService;

    public ConcurrencyService( ProjectService p, TaskService t, ProjectList projects){
        this.projectService = p;
        this.taskService = t;

    };

    class TaskUpdater implements Runnable{

        private final String taskId;
        private final Status status;
        private final Priority priority;

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
                taskService.getById(taskId).setStatusAndPriority(status, priority);

            } catch (InterruptedException e) {
                System.out.println("they didn't let me sleep");

            }
        }
    }

    public void runConcurrentUpdates(){
        projectService.createSoftwareProject("alpha", "desc", "s", 4, 140);
        taskService.createTask("task1", "P001", Status.PENDING, Priority.MEDIUM);
        taskService.createTask("task2", "P001", Status.PENDING, Priority.HIGH);
        taskService.createTask("task3", "P001", Status.PENDING, Priority.CRITICAL);

        try (ExecutorService executor = Executors.newFixedThreadPool(2)) {

            Runnable updater1 = new TaskUpdater("T001", Status.STARTED, Priority.LOW);

            Runnable updater2 = new TaskUpdater("T002", Status.COMPLETED, Priority.LOW);

            Runnable updater3 = new TaskUpdater("T003", Status.COMPLETED, Priority.LOW);

            System.out.println("Starting 3 threads...");
            executor.execute(updater1);
            executor.execute(updater2);
            executor.execute(updater3);

            executor.shutdown();

        }

         System.out.println("All tasks updated safely and concurrently");
    }

}
