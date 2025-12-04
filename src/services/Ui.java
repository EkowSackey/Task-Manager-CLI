package services;

import models.Role;
import models.User;
import utils.Input;
import utils.Menus;
import utils.Printer;

public class Ui {

    public static void init(){

        User user = UserService.u;
        String name = user.getUsername();
        Role role = user.getRole();

        String roleStr = String.valueOf(role);

        Printer.printBanner("PROJECT MANAGEMENT SYSTEM");
        System.out.printf("Current User: %s (%s) ", name, roleStr);
        System.out.println("\n");
        Menus.mainMenu();
    }
}
