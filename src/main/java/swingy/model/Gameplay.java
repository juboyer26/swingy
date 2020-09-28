package swingy.model;

import java.util.Scanner;
import java.util.*;
import swingy.model.artifact.*;


public class Gameplay {
    private static Gameplay instance = null;

    private Hero hero;
    private int mapSize;
    private boolean[][] map;
    public int pointX, pointY;
    static Scanner scanner = new Scanner(System.in);
    Random random = new Random();

    // used to get instaance of either console or gui
    public static Gameplay getInstance() {
        if (instance == null) {
            instance = new Gameplay();
        }
        return instance;
    }

    public Gameplay() {

    }

    public void initTheGame(Hero hero) {
        System.out.println("Game started");
        this.hero = hero;
        
        // create the map
        int level = hero.getLevel();
        mapSize = (level - 1) * 5 + 10 - (level % 2);
        map = new boolean[mapSize][mapSize];

        // villians
        generateVillains();

        // put the hero on the map
        pointX = mapSize / 2;
        pointY = mapSize / 2;
        map[pointX][pointY] = false;

    }

    private void generateVillains() {
        int rand;
        int level = hero.getLevel();

        for (int i = 0; i < mapSize; i++) {
            for (int j = 0; j < mapSize; j++) {
                rand = random.nextInt(100);
                if ((level + 1) * 10 >= rand)
                    map[i][j] = true;
            }
        }
    }

    public Enemy generateVillain() {
        // get eneny random attack based of the heros stats

        int attack = random.nextInt((hero.getAttack() + 2 + hero.getLevel()) - (hero.getAttack() - 20)) + (hero.getAttack() - 20);
        int defense = random.nextInt((hero.defense + 2 + hero.getLevel()) - (hero.defense - 20)) + (hero.defense - 20);
        int hitPoints = random.nextInt((hero.getHp() + 2 + hero.getLevel()) - (hero.getHp() - 20)) + (hero.getHp() - 20);

        attack = attack < 0 ? -attack : attack;
        defense = defense < 0 ? -defense : defense;
        hitPoints = hitPoints < 0 ? -hitPoints : hitPoints;
        Artifact artifact = generateArtifact();

        return new Enemy("Enemy", attack, defense, hitPoints, artifact);
    }

    private Artifact generateArtifact() {
        int rand = random.nextInt(5);

        Artifact artifact = null;
        if (rand == 0)
            artifact = new Weapon("Sword", random.nextInt(5 * (hero.getLevel() + 1)) + 1);
        else if (rand == 1)
            artifact = new Helm("Bucket", random.nextInt(3 * (hero.getLevel() + 1)) + 1);
        else if (rand == 2)
            artifact = new Armor("Copper Armour", random.nextInt(4 * (hero.getLevel() + 1)) + 1);
        return artifact;
    }

    public boolean[][] getMap() {
        return map;
    }

    public void setMap(boolean[][] map) {
        this.map = map;
    }

    public int getMapSize() {
        return mapSize;
    }

    public void setMapSize(int mapSize) {
        this.mapSize = mapSize;
    }

    public Hero getHero() {
        return hero;
    }

    public void setHero(Hero hero) {
        this.hero = hero;
    }
}