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
        baseReader = new BaseReader("Day");
        csvContent = baseReader.loadCsv(this.weatherCsvPath, ',');
    }

    @Test
    void loadCsv(){
        logTools.printMessage("TEST CSV FileContent -> [" + Arrays.deepToString(csvContent.toArray()) + "]");
        Assertions.assertNotNull(csvContent);
    }


    @Test
    void getDictContent() {
        HashMap<String,HashMap<String, String>> results = baseReader.getDictContent();
        logTools.printMessage("TEST CSV HashMap -> "+results.toString());
        Assertions.assertNotNull(results);
    }

    @Test
    void getMinDifferenceValue() {
        List<String> results = baseReader.getMinDifferenceValue("MnT", "MxT", false);
        Assertions.assertNotNull(results);
    }
    @Test
    void getMinDifferenceValueAbsolute() {
        List<String> results = baseReader.getMinDifferenceValue("MnT", "MxT", true);
        Assertions.assertNotNull(results);
    }
}