package tools;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.util.List;

public class csvToolsTest {

    private Path testCsvFile;
    @BeforeEach
    void setUp() {
        testCsvFile = Path.of("./testFiles/weatherTest.csv");
    }

    @Test
    void readTestFile(){
        try {
            List<String[]> fileContent = csvTools.readFile(testCsvFile, ',');
            logTools.printMessage("fileContent = ["+fileContent+"]");
        }catch (Exception e){
            logTools.printException(e, "It was not posible to readFile!");
        }
    }



}
