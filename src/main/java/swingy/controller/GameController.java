package swingy.controller;

import swingy.view.game.*;
import swingy.model.*;
import java.io.FileWriter; // Import the FileWriter class
import java.io.IOException;
import java.util.*;
import swingy.model.artifact.*;

public class GameController {
    private Gameplay gameplay;
    private GameView view;
    private int prePointX, prePointY;
    Random random = new Random();

    public GameController(GameView view) {
        this.view = view;
        gameplay = Gameplay.getInstance();
        prePointX = 0;
        prePointY = 0;
    }

    public void onMove(String direction) {
        int x = gameplay.pointX;
        int y = gameplay.pointY;
        prePointX = x;
        prePointY = y;

        switch (direction.toLowerCase()) {
            case "north":
                y--;
                break;
            case "east":
                x++;
                break;
            case "south":
                y++;
                break;
            case "west":
                x--;
                break;
        }

        if (x < 0 || y < 0 || x >= gameplay.getMapSize() || y >= gameplay.getMapSize()) {
            view.showInfo("you win i guess");
            try {
                FileWriter fw = new FileWriter("heroes.txt", true);
                fw.write(gameplay.getHero().getAttack() + " " + gameplay.getHero().getHp() + " "
                        + gameplay.getHero().getDefense() + " " + gameplay.getHero().getExp() + " "
                        + gameplay.getHero().getLevel() + " " + gameplay.getHero().getHeroClass() + "\n");
                fw.close();
            } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
            System.exit(1);
        }

        gameplay.pointX = x;
        gameplay.pointY = y;

        if (gameplay.getMap()[y][x]) {
            view.enemyLocation();
        }
        if (gameplay.getHero().getHp() > 0)
            view.update(gameplay);
    }

    public void onFight() {
        Enemy enemy = gameplay.generateVillain();
        System.out.println("|-----------Enemy------------|");
        System.out.println("Hp          " + enemy.getHp());
        System.out.println("Attack      " + enemy.getAttack());
        System.out.println("Defence     " + enemy.getDefense());
        System.out.println(
                "Artifact    " + (enemy.getArtifact() != null ? enemy.getArtifact().getName() : "Nothing equiped"));
        System.out.println("|----------------------------|");
        int xp = enemy.getAttack() + enemy.getHp() + enemy.getDefense();
        int rand = random.nextInt(100);

        if (rand > 98)
            xp = -1;
        int result = gameplay.getHero().fight(enemy) ? xp : -1;
        if (result >= 0) {
            view.showInfo("You win, and got " + xp + "xp.");
            gameplay.getHero().setHp(gameplay.getHero().getHp() + 50);
            levelup(xp);
            gameplay.getMap()[gameplay.pointY][gameplay.pointX] = false;
            setArtifact(enemy.getArtifact());

        } else {
            view.showInfo("You got a massive beating, Game over :'(");
            System.exit(1);
        }
    }

    private void setArtifact(Artifact artifact) {
        if (artifact != null) {
            if (artifact instanceof Weapon) {
                if (gameplay.getHero().getWeapon() == null || view.replaceArtifact()) {
                    gameplay.getHero().equipWeapon((Weapon) artifact);
                    view.showInfo("You have equiped " + artifact.getName());
                }
            } else if (artifact instanceof Armor) {
                if (gameplay.getHero().getArmor() == null || view.replaceArtifact()) {
                    gameplay.getHero().equipArmor((Armor) artifact);
                    view.showInfo("You have equiped " + artifact.getName());
                }
            } else if (artifact instanceof Helm) {
                if (gameplay.getHero().getHelm() == null || view.replaceArtifact()) {
                    gameplay.getHero().equipHelm((Helm) artifact);
                    view.showInfo("You have equiped " + artifact.getName());
                }
            }
        }
    }

    public void onRun() {
        if (new Random().nextBoolean()) {
            view.showInfo("You escaped, the odds are in your favour!\n Moved to your previous position!");
            gameplay.pointX = prePointX;
            gameplay.pointY = prePointY;
        } else {
            view.showInfo("Failed to run, enemy too close, you have to fight now");
            onFight();
        }
    }

    public void levelup(int fightExp) {

        int newLevel = (gameplay.getHero().getLevel() + 1) * 1000
                + gameplay.getHero().getLevel() * gameplay.getHero().getLevel() * 450;

        if (gameplay.getHero().getExp() + fightExp >= newLevel) {
            gameplay.getHero().setLevel(gameplay.getHero().getLevel() + 1);
            gameplay.getHero().setHp(gameplay.getHero().getHp() + 100);
            gameplay.getHero().setAttack(gameplay.getHero().getAttack() + 7);
            gameplay.getHero().setDefense(gameplay.getHero().getDefense() + 10);
            view.showInfo("You have leveled up, your attack and defence increased");
        }
        gameplay.getHero().setExp(gameplay.getHero().getExp() + fightExp);
    }

    public void onStartGame() {
        view.update(gameplay);
    }
}