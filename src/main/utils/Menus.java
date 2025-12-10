package main.utils;

public class Menus {

    public static void renderMainMenu(){

        System.out.println("________________*Main Menu*_______________");
        String[] options = {"Manage Projects", "Manage Tasks", "View Status Reports", "Switch User", "Exit"};
        Printer.printOptions(options);
        System.out.println("\n\n");


    }



    public static void renderProjectCatalog(){
        System.out.println("_________________*Options*_______________");
        String[] options = {"View all Projects", "View Software Projects only", "View Hardware Projects only", "Search by Budget", "Create a Software Project", "Create a Hardware Project"};
        Printer.printOptions(options);
        System.out.println("O. Back to Main Menu");

    }

    public static void renderRegularProjectCatalog(){
        System.out.println("_________________*Options*_______________");
        String[] options = {"View all Projects", "View Software Projects only", "View Hardware Projects only", "Search by Budget"};
        Printer.printOptions(options);
        System.out.println("O. Back to Main Menu");
    }

    public static void renderTaskCatalog(){
        Printer.printBanner("*TASK CATALOG*");
        System.out.println("_________________*Options*_______________");
        String[] options = {"View All Tasks", "Create a new Task", "Update a Task", "Delete a Task"};
        Printer.printOptions(options);
        System.out.println("0.Back to Main Menu");
    }

    public static void renderRegularTaskCatalog(){
        Printer.printBanner("*TASK CATALOG*");
        System.out.println("_________________*Options*_______________");
        String[] options = {"View All Tasks", "Create a new Task"};
        Printer.printOptions(options);
        System.out.println("0.Back to Main Menu");
    }

    public static void renderTaskSubMenu(){
        System.out.println("_________________*Options*_______________");
        String[] options = {"Add a new Task", "Update a task", "Remove Task"};
        Printer.printOptions(options);
        System.out.println("0. Back to Main Menu");

    }

    public static void renderRegularTaskSubMenu(){
        System.out.println("_________________*Options*_______________");
        String[] options = {"Add a new Task"};
        Printer.printOptions(options);
        System.out.println("0. Back to Main Menu");
    }


}
