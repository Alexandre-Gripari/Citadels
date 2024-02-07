package fr.cotedazur.univ.polytech.startingpoint;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.*;
import java.util.Arrays;
import java.util.logging.Level;

import static org.junit.jupiter.api.Assertions.*;

class CsvTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final String[] testString = {"ça marche super bien!"};

    @BeforeEach
    void init(){
        MyLogger.setLogLevel(Level.OFF);
    }

    @Test
    void testAppendIntoCsvFile() throws IOException, CsvValidationException {
        Csv.appendIntoCsvFile(Csv.getFilePath(),testString);
        assertTrue(new File(Csv.getFilePath()).exists());
        CSVReader reader = new CSVReader(new FileReader(Csv.getFilePath()));
        String[] lastLine = testString;
        String[] nextLine;
        while ((nextLine = reader.readNext()) != null) {
            lastLine = nextLine;
        }
        assertTrue(Arrays.equals(testString, lastLine));
    }
    @Test
    void testReadCsvFile(){
        Csv.clearCvsFile(Csv.getFilePath());
        System.setOut(new PrintStream(outContent));
        Csv.appendIntoCsvFile(Csv.getFilePath(),new String[] {"oui"});
        Csv.readCsvFile(Csv.getFilePath());
        String consoleOutput = outContent.toString().trim();
        assertEquals("[oui]",consoleOutput);
    }
    @Test
    void testClearCsvFile(){
        System.setOut(new PrintStream(outContent));
        Csv.appendIntoCsvFile(Csv.getFilePath(),new String[] {"oui"});
        Csv.clearCvsFile(Csv.getFilePath());
        Csv.readCsvFile(Csv.getFilePath());
        String consoleOutput = outContent.toString().trim();
        assertEquals("",consoleOutput);
    }

}