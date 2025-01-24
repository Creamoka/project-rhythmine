package com.example.rhytmine;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

class Gameplay extends JPanel {
    private Timer gameTimer;
    private ArrayList<Note> notes = new ArrayList<>();
    private int score = 0;

    public Gameplay(String songName) {
        JFrame frame = new JFrame("Gameplay - " + songName);
        frame.setSize(600, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(this);
        frame.setLocationRelativeTo(null);

        setFocusable(true);
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                handleKeyPress(e.getKeyCode());
            }
        });

        spawnNotes();
        startGameLoop();

        frame.setVisible(true);
    }

    private void spawnNotes() {
        // Add single and long notes
        notes.add(new Note(0, 0, false)); // Single note in column 0
        notes.add(new Note(2, 200, true)); // Long note in column 2
    }

    private void startGameLoop() {
        gameTimer = new Timer(16, e -> {
            updateNotes();
            repaint();
        });
        gameTimer.start();
    }

    private void updateNotes() {
        for (Note note : notes) {
            note.y += 5; // Move notes downward
        }
    }

    private void handleKeyPress(int keyCode) {
        // Map keys to columns (e.g., W, E, I, O)
        int column = switch (keyCode) {
            case KeyEvent.VK_W -> 0;
            case KeyEvent.VK_E -> 1;
            case KeyEvent.VK_I -> 2;
            case KeyEvent.VK_O -> 3;
            default -> -1;
        };

        if (column != -1) {
            for (Note note : notes) {
                if (note.column == column && Math.abs(note.y - 700) < 50) { // Assume 700 is hit line
                    if (note.isLong) {
                        score += 200; // Long note score
                    } else {
                        score += 100; // Single note score
                    }
                    notes.remove(note);
                    break;
                }
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, getWidth(), getHeight());

        // Draw hit line
        g.setColor(Color.WHITE);
        g.fillRect(0, 700, getWidth(), 5);

        // Draw notes
        for (Note note : notes) {
            g.setColor(note.isLong ? Color.GREEN : Color.BLUE);
            g.fillRect(note.column * 150, note.y, 100, note.isLong ? 200 : 50);
        }

        // Draw score
        g.setColor(Color.WHITE);
        g.drawString("Score: " + score, 10, 20);
    }
}

class Note {
    int column, y;
    boolean isLong;

    public Note(int column, int y, boolean isLong) {
        this.column = column;
        this.y = y;
        this.isLong = isLong;
    }
}
