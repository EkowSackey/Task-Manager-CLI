
import models.ProjectList;
import models.UserList;
import services.MenuService;
import services.ProjectService;
import services.TaskService;
import services.UserService;


//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or

void main() {

    UserList users = new UserList();
    ProjectList projects = new ProjectList();

    ProjectService projectService = new ProjectService(projects);
    TaskService taskService = new TaskService(projects, projectService);
    UserService userService = new UserService(users);
    userService.seedUsers();

    MenuService menu = new MenuService(userService, projectService, taskService );

    boolean start = userService.login();

    if (start){
        menu.run();
    }

}
