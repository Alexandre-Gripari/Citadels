package fr.cotedazur.univ.polytech.startingpoint;
import com.opencsv.*;
import com.opencsv.exceptions.CsvException;

import java.io.*;
import java.util.Arrays;
import java.util.List;

public class Csv {
    public static void appendIntoCsvFile(String filePath, String[] result) throws IOException {
        CSVWriter writer = new CSVWriter(new FileWriter(filePath, true));
        writer.writeNext(result, false);
        writer.close();
    }

    public static void readCsvFile(String filePath) throws IOException, CsvException {
        CSVReader reader = new CSVReader(new FileReader(filePath));
        List<String[]> allRows = reader.readAll();
        for (String[] row : allRows) {
            if(!Arrays.equals(row, new String[]{""})){
                System.out.println(Arrays.toString(row));
            }
        }
        reader.close();
    }
    public static void clearCvsFile(String filePath) throws IOException {
        CSVWriter writer = new CSVWriter(new FileWriter(filePath));
        writer.writeNext(new String[]{""});
        writer.close();
    }
}
