package models;

public abstract class User {

    private String username;
    private int pin;
    private Role role;

    public User(String username, int pin, Role role){
        this.username = username;
        this.role = role;
        this.pin = pin;
    }

//    getters
    public String getUsername() {
        return username;
    }

    public Role getRole() {
        return role;
    }

//    auth
    public boolean validate(String username, int pin){
        return (username.equals(this.username) && pin == this.pin);
    }


    @Override
    public String toString() {
        return String.format(
                "\nUsername: %s\nUser Role: %s\n",
                username, role
        );
    }
}


