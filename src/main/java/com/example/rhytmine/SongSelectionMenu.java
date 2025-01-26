package com.example.rhytmine;

import java.awt.GridLayout;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

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
            "src\\main\\resources\\song\\Aarena (Knock2 Remix)\\ISOxo - Aarena (Knock2 Remix) (Ainer) [Rrealm].osu",
            "src\\main\\resources\\song\\Mean To Be (Nightcore Mix)\\ZOOTAH & GA-SUUU & Petboy - Meant To Be (Nightcore Mix) (Ainer) [Inevitable].osu",
            "src\\main\\resources\\song\\Gotta Get Thru This (Jauz Retro Future Remix)\\Daniel Bedingfield - Gotta Get Thru This (Jauz RetroFuture Remix) (Nightcore Mix) (Claren) [Getting Thru These SVs].osu",
            "src\\main\\resources\\song\\Sugar Rush (Nightcore Mix)\\Teknicolor - Sugar Rush (Nightcore Mix) (Andere) [Mir4cle (SV)].osu"
        };
        String[] audioPaths = {
            "src\\main\\resources\\song\\Elevator (Nightcore Mix)\\audio.wav",
            "src\\main\\resources\\song\\Aarena (Knock2 Remix)\\audio.wav",
            "src\\main\\resources\\song\\Mean To Be (Nightcore Mix)\\audio.wav",
            "src\\main\\resources\\song\\Gotta Get Thru This (Jauz Retro Future Remix)\\audio.wav",
            "src\\main\\resources\\song\\Sugar Rush (Nightcore Mix)\\audio.wav"
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
