package de.exxcellent.challenge;


import de.exxcellent.challenge.readers.BaseReader;
import tools.logTools;
import tools.numericTools;

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
        final String footballCsvPath = "src/main/resources/de/exxcellent/challenge/football.csv";

        // Load Weather CSV
        BaseReader weatherReader = new BaseReader("Day");

        // Get csv and hashmap to print
        List<String[]> csvContent = weatherReader.loadCsv(weatherCsvPath, ','); // Load CSV
        HashMap<String,HashMap<String, String>> dictContent = weatherReader.getDictContent(); // Get Dict to print
        logTools.printMessage("[INFO] Weather CSV FileContent -> [" + Arrays.deepToString(csvContent.toArray()) + "]");
        logTools.printMessage("[INFO] Weather CSV Dict -> "+dictContent.toString());
        List<String> results = weatherReader.getMinDifferenceValue("MnT", "MxT", false); // Get the min spread and the day.

        //If there is an error print it.
        if (results == null){
            logTools.printError("It was not possible to get results from file["+weatherCsvPath+"]");
            System.exit(0);
        }

        // Print results
        String dayWithSmallestTempSpread = results.get(0); //Day when temp spread was the smallest
        Double smallestTempSpreadValue = numericTools.parseDouble(results.get(1)); // Value of spread in the day
        System.out.printf("\nDay with smallest temperature spread is day [%s] with value [%.2f]\n\n", dayWithSmallestTempSpread, smallestTempSpreadValue);

        // Load Football CSV
        BaseReader footballReader = new BaseReader("Team");

        // Get csv and hashmap to print
        List<String[]> footballCsvContent = footballReader.loadCsv(footballCsvPath, ','); // Load CSV
        HashMap<String,HashMap<String, String>> footballDictContent = footballReader.getDictContent(); // Get Dict to print
        logTools.printMessage("[INFO] Football CSV FileContent -> [" + Arrays.deepToString(footballCsvContent.toArray()) + "]");
        logTools.printMessage("[INFO] Football CSV Dict -> "+footballDictContent.toString());
        List<String> footballResults = footballReader.getMinDifferenceValue("Goals", "Goals Allowed", true); // Get the min spread and the day.ç

        // Print results
        String teamWithSmallestGoalSpread = footballResults.get(0); //Day when temp spread was the smallest
        Double smallestGoalSpreadValue = numericTools.parseDouble(footballResults.get(1)); // Value of spread in the day
        System.out.printf("\nTeam with smallest goals spread is team [%s] with value [%.2f]\n\n", teamWithSmallestGoalSpread, smallestGoalSpreadValue);

    }
}
