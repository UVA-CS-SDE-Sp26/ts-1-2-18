import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CipherTest {

    @Test
    void testWithFile() {
        Cipher cipher = new Cipher("Ifmmp Xpsme!");
        String result = cipher.decipher("key.txt");
        assertEquals("Hello World!", result);
    }

    @Test
    void testPunctuationAndCase() {
        String key = "ABCDE\nVWXYZ";
        Cipher cipher = new Cipher("W, x Y!", key);
        assertEquals("B, c D!", cipher.decipher());
    }

    @Test
    void testIncompleteKeyFile() {
        String key = "ONLY_ONE_LINE";
        Cipher cipher = new Cipher("Hello", key);
        assertEquals("Hello", cipher.decipher());
    }

    @Test
    void testKeyMismatchLength() {
        String key = "ABC\nXY";
        Cipher cipher = new Cipher("XYZ", key);
        assertEquals("ABZ", cipher.decipher());
    }

    @Test
    void testNullAndEmpty() {
        Cipher cipher = new Cipher(null, null);
        assertEquals("", cipher.decipher());
    }
}