// import statements
import org.mockito.Mockito;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.io.FileNotFoundException;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

/**
 * cipherMockitoTest class simulates the cipher object using a simple mocking test
 * cipherMockitoTest class mocks the cipher decipher method
 * please note that Microsoft Copilot was used to streamline test creation process
 * by manual input of strategies and different test design frameworks to simulate
 * dependencies and objects using IntelliJ IDEA and Mockito test framework as well
 * as suggest which tests would be most helpful to show the functionality
 * of cipher's behaviors and attributes
 * @author Aaryav Walter, references as cited above including Microsoft Copilot
 * @version 1.0
 */
@ExtendWith(MockitoExtension.class)
class CipherTest {
    @Test
    void spy_callsRealDecipher_andIsVerifiedByMockito() {
        // instantiate cipher object
        Cipher realCipher = new Cipher();

        // ppy wraps the real object
        Cipher spyCipher = Mockito.spy(realCipher);

        // set internal state using your test-only setters
        spyCipher.setActual("abc");
        spyCipher.setCipher("xyz");

        // cipher decipher method called
        String result = spyCipher.decipher("xzy", "xyz");

        // test result and verification
        assertEquals("acb", result);
        verify(spyCipher).decipher("xzy", "xyz");
    }

    @Test
    void loadKeyOne() throws FileNotFoundException {
        // test one
        // throws file not found exception because nonexistent file path is inputted
        Cipher loadKeyOne = new Cipher();
        assertThrows(FileNotFoundException.class, () -> {
            loadKeyOne.loadKey("this_file_does_not_exist.txt");
        });
    }

    @Test
    void loadKeyTwo() throws FileNotFoundException {
        // test two
        // throws file not found exception because file path parameter is empty string
        Cipher loadKeyTwo = new Cipher();
        loadKeyTwo.setCipher("newcipher");
        assertThrows(FileNotFoundException.class, () -> {
            loadKeyTwo.loadKey("");
        });
    }

    @Test
    void decipher() {
        // test one
        // success
        Cipher decipherOne = new Cipher();
        decipherOne.setActual("abcdefghijklmnopqrstuvwxyz");
        decipherOne.setCipher("cdefghijklmnopqrstuvwxyzab");
        assertEquals("hello", decipherOne.decipher("jgnnq", decipherOne.getCipher()));

        // test two
        // global variables have different sizes
        // fails
        Cipher decipherTwo = new Cipher();
        decipherTwo.setActual("abcdefghijklmnopqrstuvwxyz");
        decipherTwo.setCipher("cdefghijklmnopqrstuvwxyzabr");
        assertEquals(null, decipherTwo.decipher("greetings", decipherTwo.getCipher()));

        // test three
        // actual string instantiated to empty string
        // cipher key string not instantiated
        // fails
        Cipher decipherThree = new Cipher();
        decipherThree.setActual("");
        assertEquals(null, decipherThree.decipher("sun", decipherThree.getCipher()));

        // test four
        // cipher key string not instantiated
        // fails
        Cipher decipherFour = new Cipher();
        decipherFour.setActual("abcdefghijklmnopqrstuvwxyz");
        assertEquals(null, decipherFour.decipher("value", decipherFour.getCipher()));

        // test five
        // actual string not instantiated
        // fails
        Cipher decipherFive = new Cipher();
        decipherFive.setCipher("cdefghijklmnopqrstuvwxyzab");
        assertEquals(null, decipherFive.decipher("anything", decipherFive.getCipher()));
    }

    @Test
    void getCipher() {
        // test one
        Cipher getCipherOne = new Cipher();
        getCipherOne.setCipher("amazing");
        assertEquals("amazing", getCipherOne.getCipher(), "return 'amazing'");

        // test two
        Cipher getCipherTwo = new Cipher();
        assertEquals("", getCipherTwo.getCipher(), "return empty string");
    }

    @Test
    void getActual() {
        // test one
        Cipher getActualOne = new Cipher();
        getActualOne.setCipher("learning");
        assertEquals("learning", getActualOne.getCipher(), "return 'learning'");

        // test two
        Cipher getActualTwo = new Cipher();
        assertEquals("", getActualTwo.getCipher(), "return empty string");
    }
    // mock cipher object
    @Mock
    Cipher cipher;
    // use stubbed value to simulate cipher
    @Test
    void mock_decipher_returnsStubbedValue() {
        // stub behavior
        when(cipher.decipher("abc", "xyz")).thenReturn("hello");

        // test/mock decipher attribute
        String result = cipher.decipher("abc", "xyz");

        // test result and verification
        assertEquals("hello", result);
        verify(cipher).decipher("abc", "xyz");
    }
}
