package com.example.rhytmine;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.List;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

class Gameplay extends JPanel {
    private Timer gameTimer;
    private List<Note> notes;
    private int score = 0;
    private long startTime;
    private Clip audioClip;

    private BufferedImage noteSkin;

    private static final int COLUMN_WIDTH = 100;
    private static final int NUM_COLUMNS = 4;
    private static final int GAMEPLAY_WIDTH = COLUMN_WIDTH * NUM_COLUMNS;
    private int gameplayXOffset;

    public Gameplay(String songName, List<Note> beatmapNotes, String audioPath) {
        this.notes = beatmapNotes;

        try {
            noteSkin = ImageIO.read(new File("src\\main\\resources\\note\\mania-note1.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        JFrame frame = new JFrame("Gameplay - " + songName);
        frame.setSize(600, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(this);
        frame.setLocationRelativeTo(null);

        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setUndecorated(true);

        gameplayXOffset = (frame.getWidth() - GAMEPLAY_WIDTH) / 2;

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
        gameTimer = new Timer(16, e -> {
            updateNotes();
            repaint();
        });
        gameTimer.start();
    }

    private void updateNotes() {
        if (audioClip == null) return;

        long audioTime = audioClip.getMicrosecondPosition() / 1000;
        for (Note note : notes) {
            if (!note.isHit) {
                long deltaTime = note.time - audioTime;
                note.y = 700 - (int) (deltaTime * 0.5);

                if (note.y > getHeight()) {
                    note.isHit = true; // Tandai sebagai miss
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
                    score += 100;
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
        g.fillRect(gameplayXOffset, 700, GAMEPLAY_WIDTH, 5);

        for (Note note : notes) {
            if (!note.isHit) {
                int x = gameplayXOffset + note.column * COLUMN_WIDTH;
                g.drawImage(noteSkin, x, note.y, COLUMN_WIDTH, 50, null);
            }
        }

        g.setColor(Color.WHITE);
        g.drawString("Score: " + score, 10, 20);
    }

    static class Note {
        int column, y, time;
        boolean isHit;

        public Note(int column, int time) {
            this.column = column;
            this.time = time;
            this.y = -200;
            this.isHit = false;
        }
    }
}
