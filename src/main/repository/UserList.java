package main.repository;

import main.exceptions.UserNotFoundException;
import main.models.User;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

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
    private final ReadWriteLock rw = new ReentrantReadWriteLock();

    public void addUser(User user){
        rw.writeLock().lock();

        for (User u: users){
            if (u.getUsername().equals(user.getUsername())){
                System.out.println("User with this username already exists");
                return;
            }
        }

        try {
            users.add(user);
        } finally{
            rw.writeLock().unlock();
        }
    }

    public List<User> getUsers() {
        rw.readLock().lock();

        try{
            return List.copyOf(users);
        } finally {
            rw.readLock().unlock();
        }

    }

    public User findByUsername(String username){
        rw.readLock().lock();
        try{
            for (User u: users){
                if (u.getUsername().equals(username)){
                    return u;
                }
            }
            throw new UserNotFoundException("User with username " + username + " not found");
        }
        finally {
            rw.readLock().unlock();
        }


    }
}
