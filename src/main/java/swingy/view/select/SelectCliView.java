package swingy.view.select;

import swingy.util.Create;
import swingy.view.create.CreateCliView;
import swingy.view.game.GameCliView;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;
import swingy.controller.SelectController;

public class SelectCliView implements SelectView{
    //static Scanner scanner = new Scanner(System.in);
    private SelectController controller;
    HashMap<Integer, String> heroes = new HashMap<Integer, String>();

    @Override
    public void start(){
        controller = new SelectController(this);
        userInput();
    }

    @Override
    public void showInfo(String message) {
        System.out.println(message);
    }

    @Override
    public void userInput() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter player name:");
        Create.printSeperator(20);
        final String nameInput = scanner.next();
        heroes = controller.listHero();
        if(heroes.size() == 0){
            Create.clearConsole();
            Create.printSeperator(40);
            System.out.println("No heroes available");
            Create.printHeading("Create a hero");
            selectHero();
        }
        else{
            System.out.println("Please select a Hero");
            System.out.println("Available Heros: ");
            Create.printSeperator(30);
            System.out.println("Attack | HP | Defense | Exp | Level | Class");
        
            int j = 1; 
            while (j <= heroes.size()) {
                System.out.println("(" + j + ") " + heroes.get(j));
                j++;
            }
            
            while (scanner.hasNext()) {
                final int heroNum = scanner.nextInt();
                try {   
                    controller.createHero(nameInput, heroNum);
                    break;
                } catch (InputMismatchException e) {
                    System.out.println("This is not an integer");
                    break;
                }
            }
            scanner.close();
        }
    }

    @Override
    public void openGame() {
        new GameCliView().start();    
    }

    @Override
    public void selectHero(){
        new CreateCliView().start();
    }

}
