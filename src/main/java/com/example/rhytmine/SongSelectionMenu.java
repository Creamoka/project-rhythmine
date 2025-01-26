package com.example.rhytmine;

import javax.swing.*;
import java.awt.*;
import java.util.List;

class SongSelectionMenu extends JFrame {
    public SongSelectionMenu() {
        setTitle("Song Selection");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setUndecorated(true);
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 1, 5, 5));

        String[] songNames = {
            "Elevator (Nightcore Mix)",
            "Aarena (Knock2 Remix)",
            "Mean To Be (Nightcore Mix)",
            "Gotta Get Thru This (Jauz Remix)",
            "Sugar Rush (Nightcore Mix)"
        };
        String[] osuPaths = {
            "src\\main\\resources\\song\\Elevator (Nightcore Mix)\\Eliminate - Elevator (Nightcore Mix) (Ainer) [Levitation].osu",
            "resources/song/Aarena (Knock2 Remix)/ISOxo - Aarena (Knock2 Remix) (Ainer) [Rrealm].osu",
            "resources/song/Mean To Be (Nightcore Mix)/ZOOTAH & GA-SUUU & Petboy - Meant To Be (Nightcore Mix) (Ainer) [Inevitable].osu",
            "resources/song/Gotta Get Thru This (Jauz Remix)/Daniel Bedingfield - Gotta Get Thru This (Jauz RetroFuture Remix) (Nightcore Mix) (Claren) [Getting Thru These SVs].osu",
            "resources/song/Sugar Rush (Nightcore Mix)/Teknicolor - Sugar Rush (Nightcore Mix) (Andere) [Mir4cle (SV)].osu"
        };
        String[] audioPaths = {
            "src\\main\\resources\\song\\Elevator (Nightcore Mix)\\audio.wav",
            "resources/song/Aarena (Knock2 Remix)/audio.ogg",
            "resources/song/Mean To Be (Nightcore Mix)/audio.mp3",
            "resources/song/Gotta Get Thru This (Jauz Remix)/audio.mp3",
            "resources/song/Sugar Rush (Nightcore Mix)/audio.mp3"
        };

        for (int i = 0; i < songNames.length; i++) {
            int index = i;
            JButton songButton = new JButton(songNames[i]);
            songButton.addActionListener(e -> {
                List<Gameplay.Note> beatmapNotes = OsuParser.parseOsuFile(osuPaths[index]);
                if (beatmapNotes.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Failed to load beatmap!");
                    return;
                }
                new Gameplay(songNames[index], beatmapNotes, audioPaths[index]);
                dispose();
            });
            panel.add(songButton);
        }

        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> dispose());
        panel.add(backButton);

        add(panel);
        setVisible(true);
    }

    public static void main(String[] args) {
        new SongSelectionMenu();
    }
}
