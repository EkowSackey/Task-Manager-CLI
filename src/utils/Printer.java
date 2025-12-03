package utils;

import models.*;

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


//
//    }
}
