package com.example.rhytmine;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class OsuParser {
    public static class Note {
        public int column;
        public int time;
        public boolean isLong;

        public Note(int column, int time, boolean isLong) {
            this.column = column;
            this.time = time;
            this.isLong = isLong;
        }
    }

    public static List<Note> parseOsuFile(String filePath) {
        List<Note> notes = new ArrayList<>();
        boolean isHitObjects = false;

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;

            while ((line = reader.readLine()) != null) {
                if (line.startsWith("[HitObjects]")) {
                    isHitObjects = true;
                    continue;
                }

                if (isHitObjects) {
                    // Mengabaikan baris kosong atau komentar
                    if (line.trim().isEmpty() || line.startsWith("//")) {
                        continue;
                    }

                    String[] parts = line.split(",");
                    if (parts.length < 5) {
                        continue; // Mengabaikan baris yang tidak lengkap
                    }

                    try {
                        int column = Integer.parseInt(parts[0]) % 4; // Asumsi 4 kolom
                        int time = Integer.parseInt(parts[2]);
                        boolean isLong = parts.length > 5; // Jika ada kolom lebih dari 5, berarti objek panjang (slider)

                        // Menambahkan note ke dalam list
                        notes.add(new Note(column, time, isLong));
                    } catch (NumberFormatException e) {
                        // Jika ada kesalahan dalam parsing angka, lewati baris tersebut
                        e.printStackTrace();
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return notes;
    }
}
