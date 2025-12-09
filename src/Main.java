
import main.repository.ProjectList;
import main.repository.UserList;
import main.services.MenuService;
import main.services.ProjectService;
import main.services.TaskService;
import main.services.UserService;


//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or

void main() {

    UserList users = new UserList();
    ProjectList projects = new ProjectList();

    ProjectService projectService = new ProjectService(projects);
    TaskService taskService = new TaskService(projects);
    UserService userService = new UserService(users);
    userService.seedUsers();

    MenuService menu = new MenuService(userService, projectService, taskService );

    boolean start = userService.login();

    if (start){
        menu.run();
    }

}
