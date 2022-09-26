package de.exxcellent.challenge;


import tools.csvTools;
import tools.logTools;

import java.nio.file.Path;
import java.util.List;

/**
 * The entry class for your solution. This class is only aimed as starting point and not intended as baseline for your software
 * design. Read: create your own classes and packages as appropriate.
 *
 * @author Benjamin Schmid <benjamin.schmid@exxcellent.de>
 */
public final class App {

    /**
     * This is the main entry method of your program.
     * @param args The CLI arguments passed
     */
    public static void main(String... args) {
        Path csvFile = Path.of("../resources/weather.csv");

        try {
            List<String[]> fileContent = csvTools.readFile(csvFile, ',');
            logTools.printMessage("fileContent = ["+fileContent+"]");
        }catch (Exception e){
            logTools.printException(e, "It was not posible to readFile!");
        }

        String dayWithSmallestTempSpread = "Someday";     // Your day analysis function call …
        System.out.printf("Day with smallest temperature spread : %s%n", dayWithSmallestTempSpread);

        String teamWithSmallestGoalSpread = "A good team"; // Your goal analysis function call …
        System.out.printf("Team with smallest goal spread       : %s%n", teamWithSmallestGoalSpread);
    }
}
