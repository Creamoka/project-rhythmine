package com.example.rhytmine;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.List;

class Gameplay extends JPanel {
    private Timer gameTimer;
    private List<Note> notes;
    private int score = 0;
    private long startTime;
    private Clip audioClip;

    public Gameplay(String songName, List<Note> beatmapNotes, String audioPath) {
        this.notes = beatmapNotes;

        JFrame frame = new JFrame("Gameplay - " + songName);
        frame.setSize(600, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(this);
        frame.setLocationRelativeTo(null);

        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setUndecorated(true);

        setFocusable(true);
        addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyPressed(java.awt.event.KeyEvent e) {
                handleKeyPress(e.getKeyCode());
            }
        });

        startGameLoop();
        playAudio(audioPath);

        frame.setVisible(true);
    }

    private void startGameLoop() {
        startTime = System.currentTimeMillis();
        gameTimer = new Timer(16, e -> {
            updateNotes();
            repaint();
        });
        gameTimer.start();
    }

    private void updateNotes() {
        long currentTime = System.currentTimeMillis() - startTime;
        for (Note note : notes) {
            if (!note.isHit && note.time <= currentTime) {
                note.y += 5; // Move notes downward
                if (note.y > getHeight()) {
                    note.isHit = true; // Mark as missed
                }
            }
        }
    }

    private void handleKeyPress(int keyCode) {
        int column = switch (keyCode) {
            case java.awt.event.KeyEvent.VK_W -> 0;
            case java.awt.event.KeyEvent.VK_E -> 1;
            case java.awt.event.KeyEvent.VK_I -> 2;
            case java.awt.event.KeyEvent.VK_O -> 3;
            default -> -1;
        };

        if (column != -1) {
            for (Note note : notes) {
                if (!note.isHit && note.column == column && Math.abs(note.y - 700) < 50) {
                    score += note.isLong ? 200 : 100;
                    note.isHit = true;
                    break;
                }
            }
        }
    }

    private void playAudio(String audioPath) {
        new Thread(() -> {
            try {
                File audioFile = new File(audioPath);
                if (!audioFile.exists()) {
                    System.err.println("Audio file not found: " + audioPath);
                    return;
                }

                AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
                audioClip = AudioSystem.getClip();
                audioClip.open(audioStream);
                audioClip.start();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, getWidth(), getHeight());

        g.setColor(Color.WHITE);
        g.fillRect(0, 700, getWidth(), 5);

        for (Note note : notes) {
            if (!note.isHit) {
                g.setColor(note.isLong ? Color.GREEN : Color.BLUE);
                g.fillRect(note.column * 150, note.y, 100, note.isLong ? 200 : 50);
            }
        }

        g.setColor(Color.WHITE);
        g.drawString("Score: " + score, 10, 20);
    }

    static class Note {
        int column, y, time;
        boolean isLong, isHit;

        public Note(int column, int time, boolean isLong) {
            this.column = column;
            this.time = time;
            this.isLong = isLong;
            this.y = -200;
            this.isHit = false;
        }
    }
}
