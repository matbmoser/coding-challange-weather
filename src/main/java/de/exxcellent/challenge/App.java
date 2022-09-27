package de.exxcellent.challenge;


import de.exxcellent.challenge.readers.WeatherReader;
import tools.csvTools;
import tools.logTools;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;


public final class App {

    /**
     * This is the main entry method of your program.
     * @param args The CLI arguments passed
     */
    public static void main(String... args) {
        // Set default path to weatherCSV
        final String weatherCsvPath = "src/main/resources/de/exxcellent/challenge/weather.csv";

        // Load CSV
        WeatherReader weatherReader = new WeatherReader();
        List<String[]> csvContent = weatherReader.loadCsv(weatherCsvPath, ','); // Load CSV
        HashMap<Float,HashMap<String, Float>> dictContent = weatherReader.getDictContent(); // Load Dict Manually
        logTools.printMessage("[INFO] CSV FileContent -> [" + Arrays.deepToString(csvContent.toArray()) + "]");
        logTools.printMessage("[INFO] CSV Dict -> "+dictContent.toString());

        List<Float> results = weatherReader.getMinSpreadDayAndValue(); // Get the min spread and the day.

        //If there is an error print it.
        if (results == null){
            logTools.printError("It was not possible to get results from file["+weatherCsvPath+"]");
            System.exit(0);
        }

        // Print results
        Float dayWithSmallestTempSpread = results.get(0); //Day when temp spread was the smallest
        Float smallestTempSpreadValue = results.get(1); // Value of spread in the day
        System.out.printf("Day with smallest temperature spread is day [%2.0f] with value [%.2f] %n", dayWithSmallestTempSpread, smallestTempSpreadValue);




        String teamWithSmallestGoalSpread = "A good team"; // Your goal analysis function call …
        System.out.printf("Team with smallest goal spread       : %s%n", teamWithSmallestGoalSpread);
    }
}
