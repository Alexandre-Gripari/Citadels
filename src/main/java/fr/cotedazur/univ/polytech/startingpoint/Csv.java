package fr.cotedazur.univ.polytech.startingpoint;
import com.opencsv.*;

import java.io.*;
import java.util.List;
import java.util.logging.Level;

public class Csv {
    private Csv() {}

    public static String getFilePath() {
        return FILEPATH;
    }

    private static final String FILEPATH = "stats/gamestats.csv";
    public static void createFile(String filePath) throws IOException {
        File fichier = new File(filePath);
            if (fichier.getParentFile().mkdirs() && (!fichier.createNewFile())) {MyLogger.log(Level.WARNING, "Impossible de créer le fichier");
            }
    }

    public static void appendIntoCsvFile(String filePath, List<String[]> result) throws IOException {
        createFile(filePath);
        CSVWriter writer = new CSVWriter(new FileWriter(filePath, false));
        writer.writeAll(result, false);
        writer.close();
    }
}
