package models;

public class SoftwareProject extends Project{

    public SoftwareProject(String ID, String name, String type, String description, int teamSize, double budget ){
        super(ID, name, "Software Project", description, teamSize, budget);
    }

}
