package main.services;

import main.exceptions.UserNotFoundException;
import main.models.Role;
import main.models.User;
import main.repository.UserList;
import main.utils.Input;
import main.utils.Printer;

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
                Printer.printError(e.getMessage());
                continue;
            }

            int pin = Input.readInt("Input PIN: ");
            if (user.validate(user.getUsername(), pin)) {
                Printer.printSuccess(String.format("Welcome %s. \n", user.getUsername()));
                u = user;
                return true;

            } else {
                Printer.printError("Invalid credentials! Try again.\n\n");

            }
        }

    }

    public  void clearUser(){
        u = null;
    }
}
