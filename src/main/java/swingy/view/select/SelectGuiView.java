package swingy.view.select;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import swingy.view.create.CreateGuiView;
import swingy.view.game.GameGuiView;

import java.util.HashMap;

import swingy.Main;
import swingy.controller.SelectController;

public class SelectGuiView extends JPanel implements SelectView {

    private static final long serialVersionUID = 1L;
    private JLabel playerNameLabel = new JLabel("Enter a name:");
    private JTextField playerNameInput = new JTextField(10);
    private JButton startButton = new JButton("Start Game");
    private JEditorPane dataInfo = new JEditorPane();
    JButton createButton = new JButton("Create");

    private SelectController controller;
    // private int lastSelectedIdx;
    HashMap<Integer, String> heroes = new HashMap<Integer, String>();

    @Override
    public void start() {
        controller = new SelectController(this);
        guiBuilder();
    }

    private void guiBuilder() {
        Main.getFrame().setTitle("Select Hero");
        this.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;

        heroes = controller.listHero();
        String[] data = new String[heroes.size()];

        startButton.setFocusable(false);
        startButton.setEnabled(false);
        int j = 1;
        int i = 0;

        while (j <= heroes.size()) {
            data[i] = heroes.get(j);
            j++;
            i++;
        }
        final JList<String> list = new JList<>(data);

        if (heroes.size() == 0) {
            dataInfo.setEditable(false);
            dataInfo.setPreferredSize(new Dimension(250, 60));
            dataInfo.setMinimumSize(new Dimension(200, 200));
            dataInfo.setText("No heroes available , create a new hero");

            c.gridx = 0;
            c.gridy = 0;
            this.add(dataInfo, c);

            c.gridx = 0;
            c.gridy = 1;
            this.add(createButton, c);
        } else {
            list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            list.setLayoutOrientation(JList.VERTICAL);
            list.setVisibleRowCount(-1);

            JScrollPane listScroll = new JScrollPane(list);
            listScroll.setPreferredSize(new Dimension(200, heroes.size() * 25));
            listScroll.setMinimumSize(new Dimension(150, heroes.size() * 25));
            
            dataInfo.setEditable(false);
            dataInfo.setText("Attack/HP/Defense/Exp/Level/Class");
            listScroll.add(dataInfo);

            c.gridx = 0;
            c.gridy = 1;
            this.add(dataInfo, c);

            JPanel createHeroPanel = new JPanel();
            createHeroPanel.add(playerNameLabel);
            createHeroPanel.add(playerNameInput);
            createHeroPanel.setVisible(true);

            c.gridx = 0;
            c.gridy = 0;
            this.add(createHeroPanel, c);

            c.gridx = 0;
            c.gridy = 2;
            this.add(listScroll, c);

            c.gridx = 0;
            c.gridy = 3;
            this.add(startButton, c);
        }

        Main.getFrame().add(this);
        Main.getFrame().setVisible(true);

        list.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    if (list.getSelectedIndex() != -1) {
                        startButton.setEnabled(true);
                        // lastSelectedIdx = list.getSelectedIndex();
                    } else
                        startButton.setEnabled(false);
                }
            }
        });

        startButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (list.getSelectedIndex() != -1) {
                    controller.createHero(playerNameInput.getText(), list.getSelectedIndex() + 1);
                }
            }
        });
        createButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                selectHero();
            }
        });

    }

    @Override
    public void userInput() {

    }

    @Override
    public void showInfo(String message) {
        JOptionPane.showMessageDialog(Main.getFrame(), message);
    }

    @Override
    public void openGame() {
        this.setVisible(false);
        new GameGuiView().start();
    }

    @Override
    public void selectHero() {
        this.setVisible(false);
        new CreateGuiView().start();
    }
}
