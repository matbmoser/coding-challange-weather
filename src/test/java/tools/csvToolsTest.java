package tools;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tools.csvTools;
import tools.logTools;

import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

public class csvToolsTest {

    private Path testCsvFile;
    @BeforeEach
    void setUp() {
        testCsvFile = Path.of("src/test/resources/de.exxcellent.challange/weatherTest.csv");
    }

    @Test
    void readTestFile(){
        try {
            List<String[]> fileContent = csvTools.readFile(testCsvFile, ',');
            logTools.printMessage("CSV Read Test csvTools = ["+ Arrays.deepToString(fileContent.toArray())+"]");
        }catch (Exception e){
            logTools.printException(e, "It was not posible to readFile!");
        }
    }



}
