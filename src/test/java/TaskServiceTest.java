package test.java;

import main.exceptions.ProjectNotFoundException;
import main.exceptions.TaskNotFoundException;
import main.models.Priority;
import main.models.Status;
import main.models.Task;
import main.repository.ProjectList;
import main.services.ProjectService;
import main.services.TaskService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TaskServiceTest {
    ProjectList projects = new ProjectList();
    TaskService taskService = new TaskService(projects);
    ProjectService projectService = new ProjectService(projects);

    @Test
    @DisplayName("Task Creation Test")
    public void shouldCreateTasksWithDifferentIdsAndAssignToProjectOrThrow(){
        projectService.createSoftwareProject("n","d", "", 8,1.0);
        String createdProjectId = projectService.getAllProjects().getFirst().getID();
        taskService.createTask("task", createdProjectId, Status.PENDING, Priority.MEDIUM );
        taskService.createTask("new task", createdProjectId, Status.PENDING,Priority.MEDIUM);

        String id1 = taskService.getAllTasks().getFirst().getID();
        String id2 = taskService.getAllTasks().getLast().getID();

        Assertions.assertNotEquals(id1,id2);
        Assertions.assertNotNull(taskService.getAllTasks());
        Assertions.assertNotNull(projects.getByID(createdProjectId).getTasks());
        Assertions.assertThrows(ProjectNotFoundException.class, ()-> taskService.createTask("task", "NON_EXISTENT_ID", Status.PENDING, Priority.MEDIUM ));
    }

    @Test
    @DisplayName("Task Update Test")
    public void shouldUpdateTask(){
        projectService.createSoftwareProject("n","d", "", 8,1.0);
        String createdProjectId = projectService.getAllProjects().getFirst().getID();

        taskService.createTask("task", createdProjectId, Status.PENDING, Priority.MEDIUM );
        String createdTaskId = taskService.getAllTasks().getFirst().getID();
        Status createdTaskStatus = taskService.getAllTasks().getFirst().getStatus();

        Task task = projects.getTaskByID(createdTaskId);

        taskService.updateTask(task, Status.COMPLETED, Priority.LOW);

        Status updatedStatus = task.getStatus();

        Assertions.assertNotEquals(createdTaskStatus, updatedStatus);
    }

    @Test
    @DisplayName("Task Delete Test")
    public void shouldDeleteTaskOrThrow(){
        projectService.createSoftwareProject("n","d", "", 8,1.0);
        String createdProjectId = projectService.getAllProjects().getFirst().getID();

        taskService.createTask("task", createdProjectId, Status.PENDING, Priority.MEDIUM );
        String createdTaskId = taskService.getAllTasks().getFirst().getID();

        Assertions.assertDoesNotThrow(()->taskService.deleteTask(createdTaskId));
        Assertions.assertThrows(TaskNotFoundException.class, ()->taskService.deleteTask("NON_EXISTENT_ID"));
    }
}
