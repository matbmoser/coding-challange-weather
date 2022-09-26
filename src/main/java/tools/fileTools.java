package tools;

import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;

public final class fileTools {
    public Reader readFile(Path filePath) {
        return Files.newBufferedReader(filePath);
    }


}
