package swingy.view.game;

import swingy.controller.*;
import swingy.model.*;
import java.util.Scanner;

public class GameCliView implements GameView{

    private GameController controller;
    static Scanner scanner = new Scanner(System.in);
    
    @Override
    public void start() {
        controller = new GameController(this);
        controller.onStartGame();
    }

    @Override
    public void update(Gameplay game) {
        System.out.println();
        System.out.println("|-----------------------|");
        System.out.println("|   Hp       " + game.getHero().getHp());
        System.out.println("|   Attack   " + game.getHero().getAttack());
        System.out.println("|   Defence  " + game.getHero().getDefense());
        System.out.println("|   Level    " + game.getHero().getLevel());
        System.out.println("|   Xp       " + game.getHero().getExp());
        System.out.println("|   Helmet   " + (game.getHero().getHelm() != null ? game.getHero().getHelm().getName() : "Nothing"));
        System.out.println("|   Armor    " + (game.getHero().getArmor() != null ? game.getHero().getArmor().getName() : "Nothing"));
        System.out.println("|   Weapon   " + (game.getHero().getWeapon() != null ? game.getHero().getWeapon().getName() : "Nothing"));
        System.out.println("|-----------------------|");
        System.out.println();
        System.out.println("Your position is map[" + game.pointX + "," + game.pointY + "]");
        printMap(game.getMap(), game.pointX, game.pointY);
        getUserInput();
    }

    private void getUserInput() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nNORTH, EAST, SOUTH, WEST - to move to this direction");
        System.out.println("Commands (NORTH, EAST, SOUTH, WEST):");
        while (scanner.hasNext()) {
            String input = scanner.nextLine();

            if ("north".equalsIgnoreCase(input) || "east".equalsIgnoreCase(input) || "south".equalsIgnoreCase(input)
                    || "west".equalsIgnoreCase(input)) {
                controller.onMove(input);
                break;
            }
            // } else if ("switch".equalsIgnoreCase(input)) {
            //    controller.switchGame();
            //    break;
            // }
            else{
                System.out.println("Unknown command");
            }
        }
        scanner.close();
    }

    
    @Override
    public void printMap(boolean[][] map, int x, int y) {
        System.out.printf("Map size %dx%d\n", map.length, map.length);
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if (x == j && y == i)
                System.out.print("U  ");
                else if (map[i][j])
                System.out.print("E  ");
                else
                System.out.print(".  ");
            }
            System.out.println();
        }
    }
    
    @Override
    public boolean replaceArtifact() {
        Scanner scanner = new Scanner(System.in);
        System.out.println();
        System.out.println("LEAVE - to leave your artifact");
        System.out.println("REPLACE - to replace by new artifact");
        System.out.println("Commands (LEAVE, REPLACE):");
        while (scanner.hasNext()) {
            String input = scanner.nextLine();
            
            if ("leave".equalsIgnoreCase(input)) {
                return false;
            } else if ("replace".equalsIgnoreCase(input)) {
                return true;
            } else {
                System.out.println("Unknown command");
            }
            scanner.close();
        }
        return false;
    }
    
    @Override
    public void enemyLocation() {
        scanner = new Scanner(System.in);
        System.out.println();
        System.out.println("Enemy located choose wisely");
        System.out.println("FIGHT - to fight with villain");
        System.out.println("RUN - to run, 50% chance to move to the previous position");
        System.out.println("Commands (FIGHT, RUN):");
        while (scanner.hasNext()) {
            String input = scanner.nextLine();
            
            if ("fight".equalsIgnoreCase(input)) {
                controller.onFight();
                break;
            } else if ("run".equalsIgnoreCase(input)) {
                controller.onRun();
                break;
            } else {
                System.out.println("Unknown command");
            }
        }
    }

    @Override
    public void showInfo(String message) {
       System.out.println(message);
    }

    // @Override
    // public void switchGameView(){
    //     new GameGuiView().start();
    // }
}