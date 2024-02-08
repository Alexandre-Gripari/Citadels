package fr.cotedazur.univ.polytech.startingpoint;
import com.opencsv.*;
import com.opencsv.exceptions.CsvException;

import java.io.*;
import java.util.Arrays;
import java.util.List;

public class Csv {
    private Csv() {}

    public static String getFilePath() {
        return FILEPATH;
    }

    private static final String FILEPATH = "stats/gamestats.csv";
    public static void createFile(String filePath) {
        File fichier = new File(filePath);
        try {
            if (fichier.getParentFile().mkdirs()) {
                fichier.createNewFile();
            }
        } catch (IOException e) {
            System.err.println("Erreur lors de la création du fichier : " + e.getMessage());
        }
    }

    public static void appendIntoCsvFile(String filePath, List<String[]> result) {
        createFile(filePath);
        try {
            CSVWriter writer = new CSVWriter(new FileWriter(filePath, false));
            writer.writeAll(result, false);
            writer.close();
        } catch (IOException e) {
            System.err.println("Erreur lors de l'ajout des données au fichier : " + e.getMessage());
        }
    }
}
