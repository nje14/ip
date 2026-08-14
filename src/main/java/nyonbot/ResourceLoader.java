package nyonbot;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class ResourceLoader {
    /// Reads a .txt file and returns its entire contents as one string
    /// Uses `getClassLoader()` so omit the preceding `/`
    /// @param filename path to file; omit the preceding `/`
    /// @return contents of `filename`
    /// @throws Error if read is interrupted or file cannot be found
    /// 
    public static String readTextfile(String filename) {
        try (InputStream input = NyonBot.class.getClassLoader().getResourceAsStream(filename)) {
            if (input == null) {
                throw new Error("Could not load text from "+filename);
            }
            return new String(input.readAllBytes(),StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new Error("Exception occured while reading from "+ filename + ": " + e);
        }
    }
}
