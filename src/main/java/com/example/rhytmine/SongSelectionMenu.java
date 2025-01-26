package com.example.rhytmine;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList; // Pastikan ArrayList di-import
import java.util.List;

class SongSelectionMenu extends JFrame {
    public SongSelectionMenu() {
        setTitle("Song Selection");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setUndecorated(true);
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 1, 5, 5));

        JButton song1Button = new JButton("Song 1");
        JButton song2Button = new JButton("Song 2");
        JButton song3Button = new JButton("Song 3");
        JButton song4Button = new JButton("Song 4");
        JButton song5Button = new JButton("Song 5");
        JButton backButton = new JButton("Back");

        // Button actions for each song
        song1Button.addActionListener(e -> {
            List<OsuParser.Note> beatmapNotes = OsuParser.parseOsuFile("resources/song/Elevator (Nightcore Mix)/Eliminate - Elevator (Nightcore Mix) (Ainer) [Levitation].osu");
            new Gameplay("Elevator (Nightcore Mix)", new ArrayList<>(beatmapNotes), "resources/song/Elevator (Nightcore Mix)/audio.mp3");
            dispose();
        });

        song2Button.addActionListener(e -> {
            List<OsuParser.Note> beatmapNotes = OsuParser.parseOsuFile("resources/song/Aarena (Knock2 Remix)/ISOxo - Aarena (Knock2 Remix) (Ainer) [Rrealm].osu");
            new Gameplay("Aarena (Knock2 Remix)", new ArrayList<>(beatmapNotes), "resources/song/Aarena (Knock2 Remix)/audio.mp3");
            dispose();
        });

        song3Button.addActionListener(e -> {
            List<OsuParser.Note> beatmapNotes = OsuParser.parseOsuFile("resources/song/Mean To Be (Nightcore Mix)/ZOOTAH & GA-SUUU & Petboy - Meant To Be (Nightcore Mix) (Ainer) [Inevitable].osu");
            new Gameplay("Mean To Be (Nightcore Mix)", new ArrayList<>(beatmapNotes), "resources/song/Mean To Be (Nightcore Mix)/audio.mp3");
            dispose();
        });

        song4Button.addActionListener(e -> {
            List<OsuParser.Note> beatmapNotes = OsuParser.parseOsuFile("resources/song/Gotta Get Thru This (Jauz Retro Future Remix)/Daniel Bedingfield - Gotta Get Thru This (Jauz RetroFuture Remix) (Nightcore Mix) (Claren) [Getting Thru These SVs].osu");
            new Gameplay("Gotta Get Thru This (Jauz Retro Future Remix)", new ArrayList<>(beatmapNotes), "resources/song/Gotta Get Thru This (Jauz Retro Future Remix)/audio.mp3");
            dispose();
        });

        song5Button.addActionListener(e -> {
            List<OsuParser.Note> beatmapNotes = OsuParser.parseOsuFile("resources/song/Sugar Rush (Nightcore Mix)/Teknicolor - Sugar Rush (Nightcore Mix) (Andere) [Mir4cle (SV)].osu");
            new Gameplay("Sugar Rush (Nightcore Mix)", new ArrayList<>(beatmapNotes), "resources/song/Sugar Rush (Nightcore Mix)/audio.mp3");
            dispose();
        });

        backButton.addActionListener(e -> {
            // Handle back button action, maybe return to the main menu
            dispose();
        });

        // Add buttons to panel
        panel.add(song1Button);
        panel.add(song2Button);
        panel.add(song3Button);
        panel.add(song4Button);
        panel.add(song5Button);
        panel.add(backButton);

        // Add panel to frame
        add(panel);

        // Make the frame visible
        setVisible(true);
    }

    public static void main(String[] args) {
        // Show the SongSelectionMenu when the program starts
        new SongSelectionMenu();
    }
}
