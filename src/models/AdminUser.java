package models;

public class AdminUser extends User{
    public AdminUser(String username, int pin) {
        super(username, pin, Role.ADMIN);
    }
}
