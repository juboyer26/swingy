package swingy.view.game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import swingy.Main;
import swingy.controller.GameController;
import swingy.model.Gameplay;

public class GameGuiView extends JPanel implements GameView{
    private static final long serialVersionUID = 1L;
    private GameController controller;

    private JEditorPane heroInfo = new JEditorPane();
    private JEditorPane mapInfo = new JEditorPane();
    private String options[] = { "Fight", "Run" };
    private JButton north = new JButton ("North");
    private JButton east = new JButton ("East");
    private JButton west = new JButton ("West");
    private JButton south = new JButton ("South");

    @Override
    public void start(){

        controller = new GameController(this);
        guiBuilder();
        controller.onStartGame();
    };

    public void guiBuilder()  {
        Main.getFrame().setTitle("Game");
        this.setLayout(new GridBagLayout());
        this.setVisible(true);
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.setVisible(true);

        buttonPanel.add(north, c);
        buttonPanel.add(east);
        buttonPanel.add(south);
        buttonPanel.add(west);

        heroInfo.setEditable(false);
        heroInfo.setPreferredSize(new Dimension(200, 200));
        heroInfo.setMinimumSize(new Dimension(200, 200));

        mapInfo.setEditable(false);
        mapInfo.setPreferredSize(new Dimension(200, 200));
        mapInfo.setMinimumSize(new Dimension(200, 200));
        
        c.gridx = 0;
        c.gridy = 0;
        this.add(heroInfo, c);

        c.gridx = 1;
        c.gridy = 0;
        this.add(mapInfo, c);
        
        c.gridx = 0;
        c.gridy = 3;
        this.add(buttonPanel, c);

        north.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                controller.onMove("north");
            }
        });
        east.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                controller.onMove("east");
            }
        });
        west.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                controller.onMove("west");
            }
        });
        south.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                controller.onMove("south");
            }
        });

        Main.getFrame().add(this);
        Main.getFrame().setVisible(true);

    }

    @Override
    public void showInfo(String message) {
        JOptionPane.showMessageDialog(Main.getFrame(), message);
    }

    @Override
    public void update(Gameplay game){

        heroInfo.setText( 
                         "|   Hp       " + game.getHero().getHp() + "\n" + 
                         "|   Attack   " + game.getHero().getAttack() + "\n"+ 
                         "|   Defence  " + game.getHero().getDefense()+ "\n" + 
                         "|   Level    " + game.getHero().getLevel() + "\n"+
                         "|   Xp       " + game.getHero().getExp() + "\n" + 
                         "|   Helmet   " + (game.getHero().getHelm() != null ? game.getHero().getHelm().getName() : "Nothing") + "\n" +
                         "|   Armor    " + (game.getHero().getArmor() != null ? game.getHero().getArmor().getName() : "Nothing") + "\n" +
                         "|   Weapon   " + (game.getHero().getWeapon() != null ? game.getHero().getWeapon().getName() : "Nothing") + "\n\n" +
                         " Your position is map[ " + game.pointX + ", " + game.pointY + "]" + "\n" 
        );
        printMap(game.getMap(), game.pointX, game.pointY);
        //getUserInput();
    }

    @Override
    public void printMap(boolean[][] map, int x, int y){
        StringBuilder mapData =  new StringBuilder();
        mapData.append(String.format("Map size %dx%d\n", map.length, map.length));
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if (x == j && y == i)
                    mapData.append("  U  ");
                else if (map[i][j])
                    mapData.append("  E  ");
                else
                    mapData.append("   .   ");
            }
            mapData.append("\n");
        }
        mapInfo.setText(mapData.toString());
    }


    @Override
    public boolean replaceArtifact(){

        int value = JOptionPane.showOptionDialog(Main.getFrame(), "Would you like to replace? ", "Replace or Leave ?",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
        if (value == JOptionPane.YES_OPTION)
           return true;
        else
           return false;
    }

    @Override
    public void enemyLocation(){
        int value = JOptionPane.showOptionDialog(
                Main.getFrame(),
                "Enemy located choose wisely",
                "Fight or Run ?",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE, 
                null, 
                options, 
                options[0]);

        if (value == JOptionPane.YES_OPTION)
            controller.onFight();
        else
            controller.onRun();
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() != null) {
            System.out.println(e.getSource());
            controller.onMove(e.getSource().toString());
        }
    }

    // @Override
    // public void switchGameView(){1
    //     if(Main.getFrame() != null)
    //         Main.getFrame().setVisible(false);
    //     new GameCliView().start();
    // }
}
