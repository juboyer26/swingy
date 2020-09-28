package swingy.model.artifact;

public abstract class Artifact{
    private int points;
    private String name;

    public Artifact(String name, int points) {
        this.points = points;
        this.name = name;
    }

    public int getPoints(){
        return points;
    }
    public String getName(){
        return name;
    }
}