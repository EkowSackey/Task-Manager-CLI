package test.java;

import main.exceptions.InvalidRangeException;
import main.exceptions.ProjectNotFoundException;
import main.models.Project;
import main.repository.ProjectList;
import main.services.ProjectService;
import org.junit.jupiter.api.*;

import java.util.List;

public class ProjectServiceTest {
    ProjectList projects = new ProjectList();
    ProjectService projectService = new ProjectService(projects);

    @BeforeEach
    public void setup(){
        projectService.createSoftwareProject("alpha", "description", "S", 5, 120.00);
        projectService.createHardwareProject("beta", "desc", "S", 6, 120.00);


    }

    @Test
    @DisplayName("Project creation test")
    public void shouldCreateProjectsWithDifferentIds(){
        List<Project> createdProjects = projectService.getAllProjects();
        String id1 = createdProjects.getFirst().getID();
        String id2 = createdProjects.getLast().getID();

        Assertions.assertNotNull(projects.getProjects());
        Assertions.assertNotEquals(id1, id2);
    }

    @Test
    @DisplayName("Projects Types test")
    public void shouldCreateProjectsWithDifferentTypes(){

        List<Project> createdProjects = projectService.getAllProjects();
        String swType = createdProjects.getFirst().getType();
        String hwType = createdProjects.getLast().getType();

        Assertions.assertEquals("Software Project", swType);
        Assertions.assertEquals("Hardware Project", hwType);
    }

    @Test
    @DisplayName("Project Find By ID Test")
    public void shouldFindByIdOrThrow(){

        String createdId = projectService.getAllProjects().getFirst().getID();

        Assertions.assertDoesNotThrow(()->projectService.getProjectById(createdId));
        Assertions.assertThrows(ProjectNotFoundException.class, ()-> projectService.getProjectById("NON_EXISTENT_ID"));
    }

    @Test
    @DisplayName("Project Find By Range Test")
    public void shouldFindByRangeOrThrow(){

        Assertions.assertDoesNotThrow(()->projectService.searchByRange(100.00, 400.00));
        Assertions.assertNotNull(projectService.searchByRange(100.00, 400.00));
        Assertions.assertThrows(InvalidRangeException.class, ()-> projectService.searchByRange(500.00, 20.00));
    }

}
