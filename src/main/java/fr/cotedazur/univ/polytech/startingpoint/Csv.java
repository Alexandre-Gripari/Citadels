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

    public static void appendIntoCsvFile(String filePath, String[] result) {
        createFile(filePath);
        try{
            CSVWriter writer = new CSVWriter(new FileWriter(filePath, true));
            writer.writeNext(result, false);
            writer.close();
        }catch(IOException e){
            System.err.println("Erreur lors de l'ajout des données au fichier : " + e.getMessage());
        }
    }

    public static void readCsvFile(String filePath){
        try{CSVReader reader = new CSVReader(new FileReader(filePath));
        List<String[]> allRows = reader.readAll();
        for (String[] row : allRows) {
            if(!Arrays.equals(row, new String[]{""})){
                System.out.println(Arrays.toString(row));
            }
        }
        reader.close();
        }catch(IOException e){
            System.err.println("Erreur lors de la lecture du fichier : " + e.getMessage());
        }catch(CsvException e){
            System.err.println("Erreur lors de la lecture des données : " + e.getMessage());
        }
    }
    public static void clearCvsFile(String filePath) {
        try {
            CSVWriter writer = new CSVWriter(new FileWriter(filePath));
            writer.writeNext(new String[]{""});
            writer.close();
        }catch(IOException e){
            System.err.println("Erreur lors du nettoyage du fichier : " + e.getMessage());
        }
    }
}
