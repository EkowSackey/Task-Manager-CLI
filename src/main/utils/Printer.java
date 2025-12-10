package main.utils;

import main.models.*;
import java.util.List;

public class Printer {
    public static final String RESET = "\u001B[0m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";
    private static final int WIDTH = 20;
    private static final int LARGE = 40;

    public static void printBanner(String text){

        System.out.println(BLUE + "*========================================*");
        System.out.print(BLUE+"||" + RESET);
        System.out.printf("%s", centerText(text, 38));
        System.out.print(BLUE+"||\n");
        System.out.println(BLUE + "*========================================*" + RESET);
        System.out.println("\n");
    }

    public static void printTaskTable(List<Task> tasks){

        System.out.println("________________________________________________________________________________________________________________________");
        System.out.print(centerText(YELLOW + "TASK ID" + RESET, WIDTH));
        System.out.print("|");
        System.out.print(centerText("TASK NAME", 40));
        System.out.print("|");
        System.out.print(centerText("ASSIGNED PROJECT ID", WIDTH));
        System.out.print("|");
        System.out.print(centerText("STATUS", WIDTH));
        System.out.print("|");
        System.out.print(centerText("PRIORITY", WIDTH) + "\n");
        System.out.println("________________________________________________________________________________________________________________________");

        for (Task t : tasks) {
            System.out.print(centerText(String.format(YELLOW + "%s" + RESET, t.getID()), WIDTH));
            System.out.print("|");
            System.out.print(centerText(String.format("%s", t.getName()), LARGE));
            System.out.print("|");
            System.out.print(centerText(String.format("%s", t.getAssignedProjectID()), WIDTH));
            System.out.print("|");
            System.out.print(centerText(String.format("%s", t.getStatus()), WIDTH));
            System.out.print("|");
            System.out.print(centerText(String.format("%s", t.getPriority()), WIDTH));
            System.out.println("\n");
        }

        if (tasks.isEmpty()){
            System.out.println(RED + centerText("NO TASKS TO SHOW! CREATE TASKS TO CONTINUE", 100) + RESET);
            System.out.println("\n\n");
        }

    }

    public static void printProjectTable(List<Project> projects){
        System.out.println("_____________________________________________________________________________________________________________________________________________________________________");
        System.out.print(centerText(YELLOW + "PROJECT ID" + RESET, WIDTH));
        System.out.print("|");
        System.out.print(centerText("PROJECT NAME", LARGE));
        System.out.print("|");
        System.out.print(centerText("DESCRIPTION", LARGE));
        System.out.print("|");
        System.out.print(centerText("TYPE", WIDTH));
        System.out.print("|");
        System.out.print(centerText("TEAM SIZE", WIDTH));
        System.out.print("|");
        System.out.print(centerText("PRIORITY", WIDTH) + "\n");
        System.out.println("_____________________________________________________________________________________________________________________________________________________________________");

        for (Project p : projects) {

            System.out.print(centerText(String.format(YELLOW + "%s" + RESET, p.getID()), WIDTH));
            System.out.print("|");
            System.out.print(centerText(String.format("%s", p.getName()), LARGE));
            System.out.print("|");
            System.out.print(centerText(String.format("%s", p.getDescription()), LARGE));
            System.out.print("|");
            System.out.print(centerText(String.format("%s", p.getType()), WIDTH));
            System.out.print("|");
            System.out.print(centerText(String.format("%d", p.getTeamSize()), WIDTH));
            System.out.print("|");
            System.out.print(centerText(String.format("$%.2f", p.getBudget()), WIDTH));
            System.out.println("\n");

        }

        if (projects.isEmpty()){
            System.out.println(RED + centerText("NO PROJECTS TO SHOW! CREATE PROJECTS TO CONTINUE", 140) + RESET);
            System.out.println("\n\n");
        }

    }

    public static void printReport(List<Project> projects){
        System.out.println("_____________________________________________________________________________________________");
        System.out.print(centerText(YELLOW + "PROJECT ID" + RESET, WIDTH));
        System.out.print("|");
        System.out.print(centerText("PROJECT NAME", WIDTH));
        System.out.print("|");
        System.out.print(centerText("TASKS", WIDTH));
        System.out.print("|");
        System.out.print(centerText("COMPLETED", WIDTH));
        System.out.print("|");
        System.out.print(centerText("PROGRESS", WIDTH) + "\n");
        System.out.println("_____________________________________________________________________________________________");

        double sum = 0.0;
        int num = 0;

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

            System.out.print(centerText(String.format(YELLOW + "%s" +RESET, projectID), WIDTH));
            System.out.print(centerText(String.format("%s", name), WIDTH));
            System.out.print(centerText(String.format("%d", numberOfTask), WIDTH));
            System.out.print(centerText(String.format("%d", numberOfCompleteTasks), WIDTH));
            System.out.print(centerText(String.format("%.2f%%", progress), WIDTH));
            System.out.println("\n");

        }

        double average = 0.00;
        if (num > 0){
            average = sum/num;
        }




        System.out.println("_____________________________________________________________________________________________");
        System.out.println("_____________________________________________________________________________________________");
        System.out.println(centerText(String.format(GREEN + "AVERAGE COMPLETION:  %.2f%%" + RESET, average), 100));
        System.out.println("_____________________________________________________________________________________________");
    }

    public static void printOptions(String[] options){
        int i = 0;
        for (String o : options){
            i++;
            System.out.println(i+". " + o);
        }
    }

//TODO: fix new line bug
    public static String centerText(String text, int width){
        if (text == null) text = "";
        if (width <= 0) return text;

        StringBuilder result = new StringBuilder();
        int start = 0;

        while (start < text.length()){
            int end= Math.min(start + width, text.length());
            String line = text.substring(start, end);

            int leftPadding = (width - line.length())/2;
            int rightPadding = width - line.length() - leftPadding;

            result.append(" ".repeat(leftPadding))
                    .append(line)
                    .append(" ".repeat(rightPadding));

            start += width;
        }

        return result.toString();
    }

    public static void printError(String err){
        System.out.println(RED+ err + RESET);
    }

    public static void printSuccess(String s){
        System.out.println(GREEN + s + RESET);
    }

}
