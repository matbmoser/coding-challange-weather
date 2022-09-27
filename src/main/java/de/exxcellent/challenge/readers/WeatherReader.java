package de.exxcellent.challenge.readers;

import tools.csvTools;
import tools.logTools;

import java.nio.file.Path;
import java.util.*;

public class WeatherReader {
    // Constants
    final String DAY_LABEL = "Day";
    final String MIN_TEMP_LABEL = "MnT";
    final String MAX_TEMP_LABEL = "MxT";
    // Attributes
    Path csvFile;
    Character separator = ',';
    List<String[]> fileContent;
    String filePath;
    Boolean csvLoaded;
    String[] headers;
    HashMap<Float,HashMap<String, Float>> dictContent;
    public WeatherReader(){
        //  Initialize Attributes in Constructor
        this.fileContent = new ArrayList<>();
        this.filePath = "";
        this.csvFile = null;
        this.headers = null;
        this.csvLoaded = false;
        this.dictContent = null;
    }

    public List<String[]> loadCsv(String filePath, Character separator) {
        // Load csv

        // If separator is null assign the default
        Character sep = separator;
        if (separator == null) {
            sep = this.separator;
        }
        // Initialize variables
        this.filePath = filePath;
        this.csvFile = Path.of(filePath);
        List<String[]> tmpfileContent;

        // Read the csv File
        try {
            tmpfileContent = csvTools.readFile(this.csvFile, sep);
        } catch (Exception e) {
            logTools.printException(e, "It was not posible to readFile = [" + filePath + "]!");
            return null;
        }

        // Update variables
        this.fileContent = new ArrayList<>(tmpfileContent);
        // Get headers
        this.headers = this.fileContent.remove(0);
        this.csvLoaded = true; // Check csv as loaded

        return tmpfileContent;
    }

    /*
    * getDictContent
    * Gets the content in Dict format. (HashMap)
    * If successful returns Hash Map in format:
    *           {
    *               Day: {
    *                       Object
    *                   }
    *           }
    * if not returns null
    */
    public HashMap<Float,HashMap<String, Float>> getDictContent(){
        if (this.fileContent == null || this.headers == null){
            return null;
        }
        Short i = 0;
        Float tmpCellValue;
        String tmpCell = "";
        HashMap<String,Float> tmpHashMap;
        this.dictContent = new HashMap<>();
        for (String[] row: this.fileContent){
            tmpHashMap = new HashMap<>();
            i = 0;
            for (String header: this.headers) {
                try {
                    tmpCellValue = Float.parseFloat(row[i]); //Parse int number of row
                    tmpHashMap.put(header,tmpCellValue);
                } catch (Exception e) {
                    tmpHashMap.put(header,null);
                    logTools.printException(e, "It was not possible to parse int ignoring value [" + tmpCell + "]...");
                }
                i++;
            }
            this.dictContent.put(tmpHashMap.get(DAY_LABEL),tmpHashMap);
        }
        return this.dictContent;
    }

    /*
     * getMinSpreadDayAndValue
     * Get Mininum Temperature Spread Day and Value
     *
     * Spread Temperature is
     * Returns array with (day, value) if successful.
     * if not returns null
     *
     */
    public ArrayList<Float> getMinSpreadDayAndValue(){
        if (this.fileContent == null || this.headers == null){
            return null;
        }
        if (this.dictContent==null){
            this.dictContent = this.getDictContent();
        }
        Float minSpreadDay = null;
        Float minSpreadValue = null;

        Float tmpDay;
        Float tmpSpreadValue;
        HashMap<String, Float> row;

        for (HashMap.Entry<Float,HashMap<String, Float>> dayContent : this.dictContent.entrySet()){
            row = dayContent.getValue();
            if(!row.containsKey(DAY_LABEL)) {
                logTools.printError("Day key not found! It was not possible to parse row ["+row+"]");
                continue;
            }
            if(!row.containsKey(MIN_TEMP_LABEL)) {
                logTools.printError("Min Temperature key not found! It was not possible to parse row ["+row+"]");
                continue;
            }
            if(!row.containsKey(MAX_TEMP_LABEL)) {
                logTools.printError("Max Temperature key not found! It was not possible to parse row ["+row+"]");
                continue;
            }
            tmpDay = dayContent.getKey();
            tmpSpreadValue = row.get(MAX_TEMP_LABEL) - row.get(MIN_TEMP_LABEL);
            if (minSpreadDay == null || (tmpSpreadValue < minSpreadValue) ){
                minSpreadDay = tmpDay;
                minSpreadValue = tmpSpreadValue;
            }
        }
        if (minSpreadDay == null){
            return null;
        }
        return new ArrayList<>(Arrays.asList(minSpreadDay, minSpreadValue));
    }
}