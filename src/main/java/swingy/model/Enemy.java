package swingy.model;

import swingy.model.artifact.*;

public class Enemy extends Player {

    private Artifact artifact;

    public Enemy(String name, int attack, int defense, int hitPoints, Artifact artifact) {
        super(name, attack, defense, hitPoints);
        this.artifact = artifact;
    }

    public Artifact getArtifact() {
        return artifact;
    }
}