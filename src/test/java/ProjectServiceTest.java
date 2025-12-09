package test.java;

import main.models.Project;
import main.models.ProjectList;
import main.services.ProjectService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

public class ProjectServiceTest {
    ProjectList projects = new ProjectList();
    ProjectService projectService = new ProjectService(projects);

    @Test
    @DisplayName("Project creation test")
    public void shouldCreateProjectsWithDifferentIds(){
        projectService.createSoftwareProject("alpha", "description", "S", 5, 120.00);
        projectService.createSoftwareProject("beta", "desc", "S", 6, 120.00);

        List<Project> createdProjects = projectService.getAllProjects();
        String id1 = createdProjects.getFirst().getID();
        String id2 = createdProjects.getLast().getID();

        Assertions.assertNotNull(projects.getProjects());
        Assertions.assertNotEquals(id1, id2);
    }

    @Test
    @DisplayName("Projects Types test")
    public void shouldCreateProjectsWithDifferentTypes(){
        projectService.createSoftwareProject("alpha", "description", "S", 5, 120.00);
        projectService.createHardwareProject("beta", "desc", "S", 6, 120.00);

        List<Project> createdProjects = projectService.getAllProjects();
        String swType = createdProjects.getFirst().getType();
        String hwType = createdProjects.getLast().getType();

        Assertions.assertEquals("Software Project", swType);
        Assertions.assertEquals("Hardware Project", hwType);
    }




}
