package tools;

import org.junit.jupiter.api.Test;

class logToolsTest {

    @Test
    void printMessage() {
        logTools.printMessage("This is a test!");
    }
    @Test
    void printError() {
        logTools.printError("This is an ERROR test!");
    }
    @Test
    void printException() {
        logTools.printException(new Exception(), "This is an EXCEPTION test!");
    }
    @Test
    void printWarning() {
        logTools.printWarning("This is an WARNING test!");
    }
}