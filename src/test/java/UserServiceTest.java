package test.java;

import exceptions.UserNotFoundException;
import models.UserList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import services.UserService;

public class UserServiceTest {
    UserList users = new UserList();
    UserService userService = new UserService(users);
    @Test
    public void shouldSeedUsers(){

        userService.seedUsers();


        Assertions.assertFalse(users.getUsers().isEmpty());
        Assertions.assertEquals(2, users.getUsers().size());
    }

    @Test
    public void shouldThrowNotFoundException(){
        String username = "invalid_username";
        Assertions.assertThrows(UserNotFoundException.class, ()->users.findByUsername(username));

    }

    @Test
    public void shouldReturnTrue(){
        userService.seedUsers();
        Assertions.assertTrue(users.findByUsername("ekow").validate("ekow", 121202));
    }
}
