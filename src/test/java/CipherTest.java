import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class CipherTest {

    private static final Path KEY_PATH = Paths.get("ciphers/key.txt");

    @Test
    void verifyKeyFileExistsAndFollowsConventions() throws IOException {
        assertTrue(Files.exists(KEY_PATH), "The file ciphers/key.txt must exist in the project root.");

        List<String> lines = Files.readAllLines(KEY_PATH);
        
        assertTrue(lines.size() >= 2, "Key file must have at least two lines.");
        assertFalse(lines.get(0).isEmpty(), "First line cannot be empty.");
        assertFalse(lines.get(1).isEmpty(), "Second line cannot be empty.");
        assertEquals(lines.get(0).length(), lines.get(1).length(), "Cipher line length should match Normal line length.");
    }

    @Test
    void decipherUsingActualKeyFile() throws IOException {
        assertTrue(Files.exists(KEY_PATH), "Cannot run test: ciphers/key.txt is missing.");

        String keyContent = Files.readString(KEY_PATH);
        String[] lines = keyContent.split("\\R");
        
        String plain = lines[0];
        String cipherMap = lines[1];

        int testLength = Math.min(5, plain.length());
        
        String inputCipheredSegment = cipherMap.substring(0, testLength);
        String expectedPlainSegment = plain.substring(0, testLength);

        Cipher cipher = new Cipher(keyContent, inputCipheredSegment);
        
        assertEquals(expectedPlainSegment, cipher.decipher());
    }
    
    @Test
    void decipherUsingMethodArgumentWithFileContent() throws IOException {
        assertTrue(Files.exists(KEY_PATH), "Cannot run test: ciphers/key.txt is missing.");
        
        String keyContent = Files.readString(KEY_PATH);
        String[] lines = keyContent.split("\\R");
        char lastCipherChar = lines[1].charAt(lines[1].length() - 1);
        char lastPlainChar = lines[0].charAt(lines[0].length() - 1);

        Cipher cipher = new Cipher("", String.valueOf(lastCipherChar));
        
        assertEquals(String.valueOf(lastPlainChar), cipher.decipher(keyContent));
    }
}