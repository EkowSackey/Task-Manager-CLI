package main.models;

public class HardwareProject extends Project{

    public HardwareProject(String ID, String name, String type, String description, int teamSize, double budget ){
        super(ID, name, "Hardware Project", description, teamSize, budget);
    }


}
