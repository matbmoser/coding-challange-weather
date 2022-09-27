package de.exxcellent.challenge.readers;

import tools.csvTools;
import tools.logTools;
import tools.numericTools;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class BaseReader {
    // Attributes
    Path csvFile;
    Character separator = ',';
    List<String[]> fileContent;
    String filePath;
    Boolean csvLoaded;
    String[] headers;
    HashMap<String,HashMap<String, String>> dictContent;

    String key;
    public BaseReader(String key){
        //  Initialize Attributes in Constructor
        this.fileContent = new ArrayList<>();
        this.filePath = "";
        this.csvFile = null;
        this.headers = null;
        this.csvLoaded = false;
        this.dictContent = null;
        this.key = key;
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
    public HashMap<String,HashMap<String, String>> getDictContent(){
        if (this.fileContent == null || this.headers == null || this.key == null){
            logTools.printError("CSV not loaded or key is not set!");
            return null;
        }
        Short i = 0;
        String tmpCellValue;
        String tmpCell = "";
        HashMap<String,String> tmpHashMap;
        this.dictContent = new HashMap<>();
        for (String[] row: this.fileContent){
            tmpHashMap = new HashMap<>();
            i = 0;
            for (String header: this.headers) {
                try {
                    tmpHashMap.put(header,row[i]);
                } catch (Exception e) {
                    tmpHashMap.put(header,null);
                    logTools.printException(e, "It was not possible to parse int ignoring value [" + tmpCell + "]...");
                }
                i++;
            }
            this.dictContent.put(tmpHashMap.get(this.key),tmpHashMap);
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
    public ArrayList<String> getMinDifferenceValue(String minLabel, String maxLabel, Boolean absolute){
        if (this.fileContent == null || this.headers == null){
            return null;
        }
        if (this.dictContent==null){
            this.dictContent = this.getDictContent();
        }
        String minKey = null;
        Double minValue = null;

        String tmpKey;
        Double tmpDifferenceValue;
        Double tmpMaxVal;
        Double tmpMinVal;
        HashMap<String, String> row;

        for (HashMap.Entry<String,HashMap<String, String>> dayContent : this.dictContent.entrySet()){

            row = dayContent.getValue();
            if(!row.containsKey(this.key)) {
                logTools.printError(this.key+" key not found! It was not possible to parse row ["+row+"]");
                continue;
            }
            if(!row.containsKey(minLabel)) {
                logTools.printError(minLabel+" key not found! It was not possible to parse row ["+row+"]");
                continue;
            }
            if(!row.containsKey(maxLabel)) {
                logTools.printError(maxLabel+" key not found! It was not possible to parse row ["+row+"]");
                continue;
            }
            tmpKey = dayContent.getKey();

            tmpMaxVal = numericTools.parseDouble(row.get(maxLabel));
            if (tmpMaxVal == null) {
                logTools.printError(maxLabel+" max value is a string... Just numeric values allowed.");
                continue;
            }
            tmpMinVal = numericTools.parseDouble(row.get(minLabel));
            if (tmpMinVal == null) {
                logTools.printError(minLabel+" min value is a string... Just numeric values allowed.");
                continue;
            }

            if (absolute) {
                tmpDifferenceValue = Math.abs(tmpMaxVal - tmpMinVal);
            }else {
                tmpDifferenceValue = tmpMaxVal - tmpMinVal;
            }
            if (minKey == null || (tmpDifferenceValue < minValue) ){
                minKey = tmpKey;
                minValue = tmpDifferenceValue;
            }
        }
        if (minKey == null){
            return null;
        }
        return new ArrayList<>(Arrays.asList(minKey, minValue.toString()));
    }



    // Getters and setters
    public Path getCsvFile() {
        return csvFile;
    }

    public void setCsvFile(Path csvFile) {
        this.csvFile = csvFile;
    }

    public Character getSeparator() {
        return separator;
    }

    public void setSeparator(Character separator) {
        this.separator = separator;
    }

    public List<String[]> getFileContent() {
        return fileContent;
    }

    public void setFileContent(List<String[]> fileContent) {
        this.fileContent = fileContent;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public Boolean getCsvLoaded() {
        return csvLoaded;
    }

    public void setCsvLoaded(Boolean csvLoaded) {
        this.csvLoaded = csvLoaded;
    }

    public String[] getHeaders() {
        return headers;
    }

    public void setHeaders(String[] headers) {
        this.headers = headers;
    }
    public void setDictContent(HashMap<String, HashMap<String, String>> dictContent) {
        this.dictContent = dictContent;
    }

}
