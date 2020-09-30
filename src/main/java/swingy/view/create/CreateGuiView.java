package swingy.view.create;

import swingy.controller.CreateHeroController;
import swingy.view.game.GameGuiView;
import swingy.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreateGuiView extends JPanel implements CreateView {
    private static final long serialVersionUID = 1L;
    private JLabel playerNameLabel = new JLabel("Player name:");
    private JTextField playerNameInput = new JTextField(10);
    private JButton createHeroButton = new JButton("Create Hero");
    private JLabel heroClassLabel = new JLabel("Hero class:");
    private String[] heroClasses = { "Warrior", "Archer", "Assassin", "Ranger", "Mage", "Hunter" };
    private JComboBox classesComboBox = new JComboBox(heroClasses);
    private CreateHeroController controller;
    private JEditorPane heroStats = new JEditorPane();

    @Override
    public void start() {
        controller = new CreateHeroController(this);
        guiBuilder();
    }

    private void guiBuilder() {
        Main.getFrame().setTitle("Create Hero");
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        this.setLayout(new GridBagLayout());

        heroStats.setEditable(false);
        heroStats.setPreferredSize(new Dimension(250, 120));
        heroStats.setMinimumSize(new Dimension(200, 100));
        heroStats.setText("    Class:    attack  defense   hp\n"
                        + "(1) Warrior    60      20      120\n"
                        + "(2) Archer     30      35      100\n"
                        + "(3) Assassin   25      25      90\n"
                        + "(4) Ranger     40      50      110\n"
                        + "(5) Mage       45      10      80\n"
                        + "(6) Hunter     25      20      110");

        // a panel to createButtonhold things like a div for the player name and input
        JPanel createHeroPanel = new JPanel();
        createHeroPanel.add(playerNameLabel);
        createHeroPanel.add(playerNameInput);
        createHeroPanel.setVisible(true);

        JPanel classPanel = new JPanel();
        classPanel.add(heroClassLabel);
        classPanel.add(classesComboBox);
        classPanel.setVisible(true);

        // this adds to the CreateGuiView panel
        c.gridx = 4;
        c.gridy = 0;
        this.add(createHeroPanel, c);

        c.gridx = 4;
        c.gridy = 3;
        this.add(classPanel, c);

        c.gridx = 4;
        c.gridy = 4;
        this.add(createHeroButton, c);

        c.gridx = 4;
        c.gridy = 2;
        this.add(heroStats, c);

        // Frame <- CreateGuiView panel <- createHero panel

        // this.add(createHeroButton);
        createHeroButton.setFocusable(false);

        // this add to the frame
        Main.getFrame().add(this);
        Main.getFrame().setVisible(true);

        createHeroButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.createHero(playerNameInput.getText(), classesComboBox.getSelectedIndex() + 1);
            }
        });
    }

    @Override
    public void showInfo(String message) {
        JOptionPane.showMessageDialog(Main.getFrame(), message);
    }

    @Override
    public void userInput() {

    }

    @Override
    public void openGame() {
        this.setVisible(false);
        new GameGuiView().start();
    }
}