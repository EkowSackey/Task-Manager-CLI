import models.*;
import services.Ui;
import services.UserService;
import utils.Autogen;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
void main() {

//    ProjectList projects = new ProjectList();
//
//   System.out.println(projects.getProjects());
//    SoftwareProject sw1 = new SoftwareProject(Autogen.addProject(), "Alpha","Software Project", "a sw project", 5, 3500.00  );
//    HardwareProject hw1 = new HardwareProject(Autogen.addProject(), "Gamma", "Hardware Project", "a hw project", 7, 4000.00);
//    projects.addProject(sw1);
//    projects.addProject(hw1);
//
//    Task task1 = new Task(Autogen.addTask(), "P001", "Heavy Hardware Lifting", Status.PENDING, Priority.CRITICAL);
//    Task task2 = new Task(Autogen.addTask(), "P002", "Light keyboard tapping", Status.IN_PROGRESS, Priority.MEDIUM);
//
//    projects.addTask(task1, task1.getAssignedProjectID());
//    projects.addTask(task2, task2.getAssignedProjectID());
//
//    task2.setStatus(Status.COMPLETED);
//
//    System.out.println(sw1.getCompletion());
//
//   System.out.println(projects.getSize());
//   System.out.println(projects.getProjects());
//   System.out.println(task2);
//
//   System.out.println("##############################");
//   System.out.println(projects.getTasksByPriority(Priority.CRITICAL));


    UserService.seedUsers();
    UserService.init();


    //Ui.init();
}
