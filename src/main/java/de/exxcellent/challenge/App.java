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

        List<String> weatherResults = getCsvResults(weatherCsvPath, "Weather", "Day", "MnT", "MxT", false);
        //If there is an error print it.
        if (weatherResults == null){
            logTools.printError("It was not possible to get results from file["+weatherCsvPath+"]");
            System.exit(0);
        }
        // Print results
        String dayWithSmallestTempSpread = weatherResults.get(0); //Day when temp spread was the smallest
        Double smallestTempSpreadValue = numericTools.parseDouble(weatherResults.get(1)); // Value of spread in the day
        System.out.printf("\nDay with smallest temperature spread is day [%s] with value [%.2f]\n\n", dayWithSmallestTempSpread, smallestTempSpreadValue);


        List<String> footballResults = App.getCsvResults(footballCsvPath, "Football", "Team", "Goals", "Goals Allowed", true);
        //If there is an error print it.
        if (footballResults == null){
            logTools.printError("It was not possible to get results from file["+footballCsvPath+"]");
            System.exit(0);
        }

        String teamWithSmallestGoalSpread = footballResults.get(0); //Day when temp spread was the smallest
        Double smallestGoalSpreadValue = numericTools.parseDouble(footballResults.get(1)); // Value of spread in the day
        System.out.printf("\nTeam with smallest goals spread is team [%s] with value [%.2f]\n\n", teamWithSmallestGoalSpread, smallestGoalSpreadValue);

    }
    public static List<String> getCsvResults(String weatherCsvPath, String label, String key, String minLabel, String maxLabel,Boolean absolute){
        // Load CSV
        BaseReader reader = new BaseReader(key);

        // Get csv and hashmap to print
        List<String[]> csvContent = reader.loadCsv(weatherCsvPath, ','); // Load CSV
        HashMap<String,HashMap<String, String>> dictContent = reader.getDictContent(); // Get Dict to print
        logTools.printMessage("[INFO] "+label+" CSV FileContent -> [" + Arrays.deepToString(csvContent.toArray()) + "]");
        logTools.printMessage("[INFO] "+label+" CSV Dict -> "+dictContent.toString());
        List<String> results = reader.getMinDifferenceValue(minLabel, maxLabel, absolute); // Get the min spread and the day.

        //If there is an error print it.
        if (results == null){
            logTools.printError("It was not possible to get results from file["+weatherCsvPath+"]");
            System.exit(0);
        }
        return results;
    }
}
