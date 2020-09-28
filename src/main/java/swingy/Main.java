package swingy;

import javax.swing.*;
// import java.awt.event.WindowEvent;

import swingy.util.Create;
import swingy.view.start.*;

public class Main {
    
    private static JFrame frame;

    public static void main(String[] args) {

        if (args.length != 1 || (!args[0].equals("console") && !args[0].equals("gui"))) {
            System.out.println("Usage: program console || gui");
            System.exit(1);
        }

        Create.printHeading("Welcome to swingy");

        try {
            if (args[0].equalsIgnoreCase("console"))
                new InitializeCliGame().start();
            else if (args[0].equalsIgnoreCase("gui"))
                new initializeGuiGame().start();
        } catch (Exception e) {
            System.out.println("Terminating swingy");
        }
    }

    public static JFrame getFrame(){
        if(frame == null){

            frame = new JFrame();
            frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            frame.setSize(600,600);
        }
            return frame;
    }
}