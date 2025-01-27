package com.example.rhytmine;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class OsuParser {
    public static List<Gameplay.Note> parseOsuFile(String filePath) {
        List<Gameplay.Note> notes = new ArrayList<>();
        boolean isHitObjects = false;

        File file = new File(filePath);
        if (!file.exists()) {
            System.err.println("File not found: " + filePath);
            return notes;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;

            while ((line = reader.readLine()) != null) {
                if (line.startsWith("[HitObjects]")) {
                    isHitObjects = true;
                    continue;
                }

                if (!isHitObjects || line.trim().isEmpty() || line.startsWith("//")) {
                    continue;
                }

                String[] parts = line.split(",");
                if (parts.length < 5) {
                    continue;
                }

                try {
                    int xPosition = Integer.parseInt(parts[0]);
                    int column = (xPosition % 512) / (512 / 4); // Konversi posisi x ke kolom
                    int time = Integer.parseInt(parts[2]);

                    notes.add(new Gameplay.Note(column, time));
                } catch (NumberFormatException e) {
                    System.err.println("Error parsing line: " + line);
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return notes;
    }

    public static int parseOffset(String filePath) {
        int offset = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("Offset:")) {
                    offset = Integer.parseInt(line.split(":")[1].trim());
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return offset;
    }
}
