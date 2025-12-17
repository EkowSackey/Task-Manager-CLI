package services;

import models.*;

import java.util.Scanner;

public class UserService {
    public static final UserList users = new UserList();
    static Scanner sc = new Scanner(System.in);
    public static User u = null;

    public static void seedUsers(){
        User ekow = new AdminUser("ekow", 121202);
        User saki = new RegularUser("saki", 121212);



        users.addUser(ekow);
        users.addUser(saki);
    }

    public static void init(){
        System.out.println("Input username: ");
        String username = sc.nextLine();

        User user = users.findByUsername(username);

        System.out.println("Input PIN: ");

        if( sc.hasNextInt()){
            int pin = sc.nextInt();
            sc.nextLine();

            if (user.validate(user.getUsername(), pin)){
                System.out.printf("Welcome %s. \n", user.getUsername());
                u = user;
                Ui.init();
            }
            else{
                System.out.println("Invalid credentials!");
                init();
            }


        }
        else{
            System.out.println("Please input a numeric pin.");
            sc.nextLine();
        }




    }
}
