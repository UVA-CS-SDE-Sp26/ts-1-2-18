import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CipherTest {
    @Test
    void decipherWithKeyFile(){
        Cipher cipher = new Cipher("", "");
        String str = cipher.decipher();
        assertEquals("Hello world", str, "Deciphering with the ciphers/key.txt file failed.");
    }
    
    @Test
    void decipherWithSecondArgument(){
        Cipher cipher = new Cipher("", "");
        String str = cipher.decipher("");
        assertEquals("Hello world", str, "Deciphering with command line argument failed.");
    }
}