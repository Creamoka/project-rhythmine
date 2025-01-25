package com.example.rhytmine;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenu extends JFrame {
    public MainMenu() {
        setTitle("Menu Utama");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        this.setExtendedState(Frame.MAXIMIZED_BOTH);
        this.setUndecorated(true);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 1, 10, 10));

        JButton playButton = new JButton("Play");
        JButton settingsButton = new JButton("Option");
        JButton exitButton = new JButton("Quit");

        playButton.addActionListener(e -> {
            new SongSelectionMenu();
            dispose();
        });

        settingsButton.addActionListener(e -> JOptionPane.showMessageDialog(this, "Not Available"));
        exitButton.addActionListener(e -> System.exit(0));

        panel.add(playButton);
        panel.add(settingsButton);
        panel.add(exitButton);

        add(panel);
        setVisible(true);
    }

    public static void main(String[] args) {
        new MainMenu();
    }
}
