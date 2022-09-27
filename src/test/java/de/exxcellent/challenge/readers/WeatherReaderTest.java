package de.exxcellent.challenge.readers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tools.logTools;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class WeatherReaderTest {
    private WeatherReader weatherReader;
    private final String weatherCsvPath = "src/test/resources/de.exxcellent.challange/weatherTest.csv";
    List<String[]> csvContent;

    @BeforeEach
    void setUp(){
        weatherReader = new WeatherReader();
        csvContent = weatherReader.loadCsv(this.weatherCsvPath, ',');
    }

    @Test
    void loadCsv(){
        logTools.printMessage("TEST CSV FileContent -> [" + Arrays.deepToString(csvContent.toArray()) + "]");
        Assertions.assertNotNull(csvContent);
    }


    @Test
    void getDictContent() {
        HashMap<Float,HashMap<String, Float>> results = weatherReader.getDictContent();
        logTools.printMessage("TEST CSV HashMap -> "+results.toString());
        Assertions.assertNotNull(results);
    }

    @Test
    void getMinSpreadDayAndValue() {
        List<Float> results = weatherReader.getMinSpreadDayAndValue();
        Assertions.assertNotNull(results);
    }
}