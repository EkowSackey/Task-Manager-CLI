package main.repository;

import main.exceptions.UserNotFoundException;
import main.models.User;

import java.util.ArrayList;
import java.util.List;

/**
 * This class uses a List as a data storage for users.
 * <p>This class currently represents a repository of "database level" methods for data storage, retrieval and deletion,
 * controlled by the User Service.</p>
 * <p>addUser adds a user to the list.</p>
 * <p>getUsers returns a list of users.</p>
 * <p>findByUsername finds a user by a given username.</p>
 */

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
