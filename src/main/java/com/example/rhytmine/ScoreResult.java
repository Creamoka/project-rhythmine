package com.example.rhytmine;

import javax.swing.*;
import java.awt.*;

class ScoreResult extends JFrame {
    public ScoreResult(int score) {
        setTitle("Hasil Skor");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JLabel scoreLabel = new JLabel("Score : " + score, SwingConstants.CENTER);
        JButton backButton = new JButton("Back to Main Menu");

        backButton.addActionListener(e -> {
            new MainMenu();
            dispose();
        });

        panel.add(scoreLabel, BorderLayout.CENTER);
        panel.add(backButton, BorderLayout.SOUTH);

        add(panel);
        setVisible(true);
    }
}
