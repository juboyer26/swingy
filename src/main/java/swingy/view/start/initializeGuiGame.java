package swingy.view.start;

import swingy.Main;
import swingy.controller.StartController;
import swingy.util.Create;
import swingy.view.create.*;
import swingy.view.select.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class initializeGuiGame extends JPanel implements InitializeGame{
    private static final long serialVersionUID = 1L;
    JButton createButton = new JButton("Create Hero");
    JButton selectButton = new JButton("Select Hero");

    private StartController controller;

    @Override
    public void start(){
        Create.printHeading("Started GUI");
        controller = new StartController(this);
        guiBuilder();
    }

    public void guiBuilder(){

        Main.getFrame().setTitle("start game");
        this.setLayout(new GridBagLayout());   
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        this.add(createButton, c);
        createButton.setFocusable(false);
        c.gridx = 3;
        c.gridy = 0;
        this.add(selectButton, c);
        selectButton.setFocusable(false);
        this.setVisible(true);
        Main.getFrame().add(this);
        Main.getFrame().setVisible(true);

        createButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                controller.createHero();
            }
        });

        selectButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                controller.selectHero();
            }
        });
    }

    @Override
    public void createHero(){
        this.setVisible(false);
        new CreateGuiView().start();
    }

     @Override
    public void selectHero(){
        this.setVisible(false);
        new SelectGuiView().start();
    }
}