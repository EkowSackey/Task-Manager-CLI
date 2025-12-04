package services;

import models.Project;
import utils.Input;
import utils.Printer;
import java.util.ArrayList;
import java.util.List;


public class ReportService {

    public static void init(){
        List<Project> projects = ProjectService.projects.getProjects();

        Printer.printBanner("*PROJECT STATUS*");

        List<String> ids = new ArrayList<>();
        for (Project p: projects ){
            ids.add(p.getID());
        }

        Printer.printReport(projects);

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
