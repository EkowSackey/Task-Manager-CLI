package models;

public class RegularUser extends User{
    public RegularUser(String username, int pin) {
        super(username, pin, Role.REGULAR_USER);
    }
}
