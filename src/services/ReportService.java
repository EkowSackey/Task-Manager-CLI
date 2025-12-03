package services;

import models.Project;
import models.Status;
import models.Task;
import utils.Input;
import utils.Printer;

import java.util.ArrayList;
import java.util.List;


public class ReportService {


    public static void init(){
        List<Project> projects = ProjectService.projects.getProjects();


        Printer.printBanner("*PROJECT STATUS*");

        System.out.println("_____________________________________________________________________________________________________");
        System.out.println("PROJECT ID     |        PROJECT NAME         |     TASKS    |      COMPLETED       |   PROGRESS     ");
        System.out.println("_____________________________________________________________________________________________________");

        double sum = 0;
        int num = 0;
        List<String> ids = new ArrayList<>();

        for (Project p: projects ){
            String projectID = p.getID();
            String name = p.getName();
            List<Task> tasks = p.getTasks();
            int numberOfTask = tasks.size();
            List<Task> completed = p.getByStatus(Status.COMPLETED);
            int numberOfCompleteTasks = completed.size();
            double progress = ((double) numberOfCompleteTasks / numberOfTask)*100;
            sum += progress;
            num++;
            ids.add(p.getID());

            System.out.printf("%s     |  %s     |  %d     |  %d     |  %.2f%%  ", projectID, name, numberOfTask, numberOfCompleteTasks, progress);
            System.out.println("\n");

        }

        System.out.println("_____________________________________________________________________________________________________");
        System.out.println("_____________________________________________________________________________________________________");
        System.out.printf("AVERAGE COMPLETION:  %.2f%%", (sum/num));
        System.out.println("_____________________________________________________________________________________________________");


        String choice = Input.readString("Enter a VALID Project ID to view details (0 to go back): ");

        if (ids.contains(choice)){
            ProjectService.viewDetails(choice);
        }
        else if (choice.equals("0")) {
            Ui.init();}
        else {
            System.out.println("Invalid project ID! Try again.");

        }

    }
}
