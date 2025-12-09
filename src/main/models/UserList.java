package main.models;

import main.exceptions.UserNotFoundException;

import java.util.ArrayList;
import java.util.List;

public class UserList {
    private final List<User> users = new ArrayList<User>();

    public void addUser(User user){
        for (User u: users){
            if (u.getUsername().equals(user.getUsername())){
                System.out.println("User with this username already exists");
                return;
            }
        }
        users.add(user);
    }

    public List<User> getUsers() {
        return users;
    }

    public User findByUsername(String username){
        for (User u: users){
            if (u.getUsername().equals(username)){
                return u;
            }
        }
        throw new UserNotFoundException("User with username " + username + " not found");
    }
}
