package fr.cotedazur.univ.polytech.startingpoint;

import com.opencsv.exceptions.CsvException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

class CsvTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    @Test
    void testAppendIntoCsvFile() throws IOException {
        Csv.appendIntoCsvFile("src/main/resources/stats/testappend.csv",new String[]{"oui"});
        assertTrue(new File("src/main/resources/stats/testappend.csv").exists());
        BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/stats/testappend.csv"));
        String line = reader.readLine();
        assertEquals("oui", line);
    }
    @Test
    void testReadCsvFile() throws IOException, CsvException {
        Csv.clearCvsFile("src/main/resources/stats/testread.csv");
        System.setOut(new PrintStream(outContent));
        Csv.appendIntoCsvFile("src/main/resources/stats/testread.csv",new String[] {"oui"});
        Csv.readCsvFile("src/main/resources/stats/testread.csv");
        String consoleOutput = outContent.toString().trim();
        assertEquals("[oui]",consoleOutput);
    }
    @Test
    void testClearCsvFile() throws IOException, CsvException {
        System.setOut(new PrintStream(outContent));
        Csv.appendIntoCsvFile("src/main/resources/stats/testclear.csv",new String[] {"oui"});
        Csv.clearCvsFile("src/main/resources/stats/testclear.csv");
        Csv.readCsvFile("src/main/resources/stats/testclear.csv");
        String consoleOutput = outContent.toString().trim();
        assertEquals("",consoleOutput);
    }

}