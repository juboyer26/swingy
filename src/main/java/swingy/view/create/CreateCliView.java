package swingy.view.create;

import swingy.controller.CreateHeroController;

import swingy.util.Create;
import swingy.view.game.GameCliView;

import java.util.InputMismatchException;
import java.util.Scanner;

public class CreateCliView implements CreateView{
    
    private CreateHeroController controller;

    // start game
    @Override
    public void start(){
        controller = new CreateHeroController(this);
        userInput();
    }

    // get user input
    @Override
    public void userInput() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter player name:");
        Create.printSeperator(30);
        String nameInput = scanner.nextLine();

        System.out.println("    Class:    attack  defense   hp");
        System.out.println("(1) Warrior    60      20      120");
        System.out.println("(2) Archer     30      35      100");
        System.out.println("(3) Assassin   25      25      90");
        System.out.println("(4) Ranger     40      50      110");
        System.out.println("(5) Mage       45      10      80");
        System.out.println("(6) Hunter     25      20      110");
        System.out.println("Enter class number: ");

         while (scanner.hasNext()) {
            try {
                int heroClass = scanner.nextInt();
                System.out.println(
                        "Command (1)To create Hero: name [" + nameInput + "] and Hero class [" + heroClass + "]");
                while (scanner.hasNext()) {
                    int input = scanner.nextInt();
                    try {
                        if (input == 1) {
                            controller.createHero(nameInput, heroClass);
                            break;
                        } else {
                            System.out.println("invalid choice");
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("This is not an integer");
                        break;
                    }
                }
                scanner.close();
            } catch (InputMismatchException e) {
                System.out.println("This is not an integer \n");
                userInput();
            }
        }
    }

    @Override
    public void showInfo(String message) {
        System.out.println(message);
    }

    @Override
    public void openGame(){
        new GameCliView().start();    
    }

}