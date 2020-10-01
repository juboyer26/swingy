package swingy.view.start;

import swingy.controller.StartController;
import swingy.util.Create;
import swingy.view.create.*;
import swingy.view.select.*;

public class InitializeCliGame implements InitializeGame{

    private StartController controller;

    @Override
    public void start(){
        controller = new StartController(this);
        Create.printHeading("Start console game");
        System.out.println("(1) - to create hero");
        System.out.println("(2) - to select already created hero");
        System.out.println("Choose an action:");
        Create.printSeperator(20);
        System.out.println("Commands: (1)CREATE (2)SELECT ");

        int input = Create.readInt("-> ", 2);
        if(input == 1){
            Create.clearConsole();
            Create.printHeading("Create a Hero");
            controller.createHero();
        }
        else if(input == 2){
            controller.selectHero();
        }
    }

    @Override
    public void createHero(){
        new CreateCliView().start();
    }

    @Override
    public void selectHero(){
        new SelectCliView().start();
    }
}