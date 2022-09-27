package de.exxcellent.challenge;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tools.logTools;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Example JUnit 5 test case.
 * @author Benjamin Schmid <benjamin.schmid@exxcellent.de>
 */
class AppTest {
    private String footballCsvPath;

    @BeforeEach
    public void setUp(){
        footballCsvPath = "src/test/resources/de.exxcellent.challange/footballTest.csv";
    }
    @Test
    public void getCsvResults() {
        List<String> footballResults = App.getCsvResults(footballCsvPath, "Football", "Team", "Goals", "Goals Allowed", true);
        //If there is an error print it.
        if(footballResults!=null) {
            logTools.printMessage("TEST CSV FOOTBALL TEAM = [" + footballResults.get(0) + "]");
            logTools.printMessage("TEST CSV FOOTBALL ABSOLUTE DISTANCE = [" + footballResults.get(1) + "]");
        }
        Assertions.assertNotNull(footballResults);
    }

}