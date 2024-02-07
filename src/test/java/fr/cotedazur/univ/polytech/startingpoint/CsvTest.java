package fr.cotedazur.univ.polytech.startingpoint;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import com.opencsv.exceptions.CsvValidationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;

import static org.junit.jupiter.api.Assertions.*;

class CsvTest {
    private final List<String[]> testString = new ArrayList<>();

    @BeforeEach
    void init() {
        MyLogger.setLogLevel(Level.OFF);
        testString.add(new String[]{"ça marche?"});
        testString.add(new String[]{"bien sur que non"});
        testString.add(new String[]{"yes"});
    }

    @Test
    void testAppendIntoCsvFile() throws IOException, CsvException {
        Csv.appendIntoCsvFile(Csv.getFilePath(), testString);
        assertTrue(new File(Csv.getFilePath()).exists());
        CSVReader reader = new CSVReader(new FileReader(Csv.getFilePath()));
        List<String[]> data = reader.readAll();
        for (int i = 0; i < data.size(); i++) {
            assertTrue(Arrays.deepEquals(testString.get(i), data.get(i)));
        }
    }
}