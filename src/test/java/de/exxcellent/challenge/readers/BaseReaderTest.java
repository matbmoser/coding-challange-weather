package de.exxcellent.challenge.readers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tools.logTools;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

class BaseReaderTest {
    private BaseReader baseReader;
    private final String weatherCsvPath = "src/test/resources/de.exxcellent.challange/weatherTest.csv";
    List<String[]> csvContent;

    @BeforeEach
    void setUp(){
        // Read little version of weatherCSV
        baseReader = new BaseReader("Day");
        csvContent = baseReader.loadCsv(this.weatherCsvPath, ',');
    }

    @Test
    void loadCsv(){
        // Print the content loaded
        logTools.printMessage("TEST CSV FileContent -> [" + Arrays.deepToString(csvContent.toArray()) + "]");
        Assertions.assertNotNull(csvContent);
    }


    @Test
    void getDictContent() {
        // Print the dictionary
        HashMap<String,HashMap<String, String>> results = baseReader.getDictContent();
        logTools.printMessage("TEST CSV HashMap -> "+results.toString());
        Assertions.assertNotNull(results);
    }

    @Test
    void getMinDifferenceValue() {
        // Print the normal difference
        List<String> results = baseReader.getMinDifferenceValue("MnT", "MxT", false);
        logTools.printMessage("TEST DISTANCE = ["+results.get(1)+"]");
        Assertions.assertNotNull(results);
    }
    @Test
    void getMinDifferenceValueAbsolute() {
        // Print the absolute difference
        List<String> results = baseReader.getMinDifferenceValue("MnT", "MxT", true);
        logTools.printMessage("TEST ABSOLUTE DISTANCE = ["+results.get(1)+"]");
        Assertions.assertNotNull(results);
    }
}