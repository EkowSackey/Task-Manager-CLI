package services;

import models.Role;
import models.User;
import models.UserList;
import utils.Input;



public class UserService {
    public static final UserList users = new UserList();

    public static User u = null;

    public static void seedUsers(){
        User ekow = new User("ekow", 121202, Role.ADMIN);
        User saki = new User("saki", 121212, Role.REGULAR_USER);



        users.addUser(ekow);
        users.addUser(saki);
    }

    public static void init(){

        String username = Input.readString("Input a username: ");

        User user = users.findByUsername(username);




            int pin = Input.readInt("Input PIN: ");


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

    public static void clearUser(){
        u = null;
    }
}
