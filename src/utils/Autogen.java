package utils;

import models.ProjectList;

public class Autogen {
    private static int projectID = 0;
    private static int taskID = 0;

    public static String addProject(){
        projectID ++;
        return String.format("P%03d",projectID);
    }

    public static String addTask(){
        taskID ++;
        return String.format("T%03d", taskID);
    }

}
