package main.models;

public class User {
    private int userID;
    private String username;
    private int pin;
    private Role role;

    public User(String username, int pin, Role role){
        this.username = username;
        this.role = role;
        this.pin = pin;
    }

//    getters


    public int getUserID() {
        return userID;
    }

    public String getUsername() {
        return username;
    }

    public int getPin() {
        return pin;
    }

    public Role getRole() {
        return role;
    }

//    setters


    public void setUserID(int userID) {
        this.userID = userID;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPin(int pin) {
        this.pin = pin;
    }

    public void setRole(Role role) {
        this.role = role;
    }

//    auth
    public boolean validate(String username, int pin){
        return (username.equals(this.username) && pin == this.pin);
    }


    @Override
    public String toString() {
        return String.format(
                "User ID: %d\nUsername: %s\nUser Role: %s\n",
                userID, username, role
        );
    }
}


