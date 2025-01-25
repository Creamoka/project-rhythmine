package com.example.rhytmine;

import javax.swing.*;
import java.awt.*;

class SongSelectionMenu extends JFrame {
    public SongSelectionMenu() {
        setTitle("Song Selection");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 1, 5, 5));

        JButton song1Button = new JButton("Song 1");
        JButton song2Button = new JButton("Song 2");
        JButton song3Button = new JButton("Song 3");
        JButton song4Button = new JButton("Song 4");
        JButton song5Button = new JButton("Song 5");
        JButton backButton = new JButton("Back");

        song1Button.addActionListener(e -> {
            new Gameplay("Song 1");
            dispose();
        });

        song2Button.addActionListener(e -> {
            new Gameplay("Song 2");
            dispose();
        });

        song2Button.addActionListener(e -> {
            new Gameplay("Song 3");
            dispose();
        });

        song2Button.addActionListener(e -> {
            new Gameplay("Song 4");
            dispose();
        });

        song2Button.addActionListener(e -> {
            new Gameplay("Song 5");
            dispose();
        });

        backButton.addActionListener(e -> {
            new MainMenu();
            dispose();
        });

        panel.add(song1Button);
        panel.add(song2Button);
        panel.add(song3Button);
        panel.add(song4Button);
        panel.add(song5Button);
        panel.add(backButton);

        add(panel);
        setVisible(true);
    }
}
