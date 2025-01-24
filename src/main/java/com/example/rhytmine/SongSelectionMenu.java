package com.example.rhytmine;

import javax.swing.*;
import java.awt.*;

class SongSelectionMenu extends JFrame {
    public SongSelectionMenu() {
        setTitle("Pilih Lagu");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 1, 10, 10));

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
            new Gameplay("Lagu 2");
            dispose();
        });

        backButton.addActionListener(e -> {
            new MainMenu();
            dispose();
        });

        panel.add(song1Button);
        panel.add(song2Button);
        panel.add(backButton);

        add(panel);
        setVisible(true);
    }
}
