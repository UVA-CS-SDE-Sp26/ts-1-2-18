import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import static org.junit.jupiter.api.Assertions.*;

class CipherTest {

    @Test
    void testFullIntegrationWithFile() throws IOException {
        Path tempKey = Paths.get("temp_key.txt");
        Files.writeString(tempKey, "ABC\nXYZ");

        Cipher cipher = new Cipher("", "Xy Z!");
        String result = cipher.decipher("temp_key.txt");

        assertEquals("Ab C!", result);
        Files.deleteIfExists(tempKey);
    }

    @Test
    void testPunctuationAndCase() {
        String key = "ABCDE\nVWXYZ";
        Cipher cipher = new Cipher(key, "W, x Y!");
        assertEquals("B, c D!", cipher.decipher());
    }

    @Test
    void testIncompleteKeyFile() {
        String key = "ONLY_ONE_LINE";
        Cipher cipher = new Cipher(key, "Hello");
        assertEquals("Hello", cipher.decipher());
    }

    @Test
    void testKeyMismatchLength() {
        String key = "ABC\nXY";
        Cipher cipher = new Cipher(key, "XYZ");
        assertEquals("ABZ", cipher.decipher());
    }

    @Test
    void testNullAndEmpty() {
        Cipher cipher = new Cipher(null, null);
        assertEquals("", cipher.decipher());
    }
}