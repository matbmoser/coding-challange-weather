package tools;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;

public class csvToolsTest {

    private Path testCsvFile;
    @BeforeEach
    void setUp() {
        Path testCsv = Path.of("./testFiles/weatherTest.csv");
    }

    @Test
    void readTestFile(){
        try {
            csvTools.readFile(testCsvFile, ',');
        }catch (Exception e){
            logTools.printException(e, "It was not posible to readFile!");
        }
    }



}
