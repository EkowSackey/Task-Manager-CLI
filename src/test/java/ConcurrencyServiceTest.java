package test.java;

import main.models.Status;
import main.repository.ProjectList;
import main.services.ConcurrencyService;
import main.services.ProjectService;
import main.services.TaskService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ConcurrencyServiceTest {

    @Test
    @DisplayName("Concurrent update test")
    public void shouldUpdateStatusConcurrently(){
        ProjectList prjs = new ProjectList();
        ProjectService p = new ProjectService(prjs);
        TaskService t = new TaskService(prjs);
        ConcurrencyService cnc = new ConcurrencyService(p, t, prjs);

        cnc.runConcurrentUpdates();

        Assertions.assertEquals(Status.STARTED, t.getById("T001").getStatus());
        Assertions.assertEquals(Status.COMPLETED, t.getById("T002").getStatus());
        Assertions.assertEquals(Status.COMPLETED, t.getById("T003").getStatus());
    }
}
