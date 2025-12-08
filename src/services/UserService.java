package services;

import exceptions.UserNotFoundException;
import models.Role;
import models.User;
import models.UserList;
import utils.Input;

public class UserService {
    public  UserList users;
    public  User u = null;

    public UserService(UserList users){
        this.users = users;
    }

    public void seedUsers(){
        User ekow = new User("ekow", 121202, Role.ADMIN);
        User saki = new User("saki", 121212, Role.REGULAR_USER);

        users.addUser(ekow);
        users.addUser(saki);
    }

    public boolean login(){

        while (true){
            String username = Input.readString("Input a username: ");

            User user = null;
            try {
                user = users.findByUsername(username);
            } catch (UserNotFoundException e) {
                System.out.println(e.getMessage());
                System.out.println("Try again!");
                continue;
            }

            int pin = Input.readInt("Input PIN: ");
            if (user.validate(user.getUsername(), pin)) {
                System.out.printf("Welcome %s. \n", user.getUsername());
                u = user;
                return true;

            } else {
                System.out.println("Invalid credentials! Try again.\n\n");

            }
        }

    }

    public  void clearUser(){
        u = null;
    }
}
