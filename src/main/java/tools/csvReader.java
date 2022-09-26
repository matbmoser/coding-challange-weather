package tools;

import com.opencsv.*;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class csvReader {
    public static List<String[]> readFile(Path filePath, Character separator) throws Exception {
        try (Reader reader = Files.newBufferedReader(filePath)) {
            CSVParser parser = new CSVParserBuilder()
                    .withSeparator(separator) //Set separator
                    .withIgnoreQuotations(true)//Ignore quotations
                    .build();

            // Read CSV
            try(CSVReader csvReader = new CSVReaderBuilder(reader).withSkipLines(0).withCSVParser(parser).build()) {
                return csvReader.readAll();
            }
        }
    }
}
