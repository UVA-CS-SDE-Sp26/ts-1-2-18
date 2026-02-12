import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ProgramControllerTest {

    private FileHandler mockFileHandler;
    private Cipher mockCipher;
    private ProgramController controller;

    @BeforeEach
    public void setup() {
        mockFileHandler = Mockito.mock(FileHandler.class);
        mockCipher = Mockito.mock(Cipher.class);

        // Inject mocks using a special constructor you add for testing
        controller = new ProgramController(mockFileHandler, mockCipher);
    }

    // ------------------------------------------------------------
    // LIST FILES TESTS
    // ------------------------------------------------------------

    @Test
    public void testListFiles_basic() {
        List<String> files = Arrays.asList("a.txt", "b.txt", "c.txt");
        when(mockFileHandler.getAvailableFiles()).thenReturn(files);

        String result = controller.listFiles();

        assertEquals("01 a.txt\n02 b.txt\n03 c.txt", result);
        verify(mockFileHandler, times(1)).getAvailableFiles();
    }

    @Test
    public void testListFiles_empty() {
        when(mockFileHandler.getAvailableFiles()).thenReturn(List.of());

        String result = controller.listFiles();

        assertEquals("No files found.", result);
    }

    @Test
    public void testListFiles_exception() {
        when(mockFileHandler.getAvailableFiles()).thenThrow(new RuntimeException());

        String result = controller.listFiles();

        assertEquals("ERROR: Unable to list files.", result);
    }

    // ------------------------------------------------------------
    // DISPLAY FILE TESTS
    // ------------------------------------------------------------

    @Test
    public void testDisplayFile_plainText() {
        List<String> files = Arrays.asList("a.txt");
        when(mockFileHandler.getAvailableFiles()).thenReturn(files);
        when(mockFileHandler.readFile("a.txt")).thenReturn("HELLO");

        String result = controller.displayFile("1", "");

        assertEquals("HELLO", result);
        verify(mockFileHandler).readFile("a.txt");
        verify(mockCipher, never()).decipher(anyString(), anyString());
    }

    @Test
    public void testDisplayFile_withCipher() {
        List<String> files = Arrays.asList("secret.txt");
        when(mockFileHandler.getAvailableFiles()).thenReturn(files);
        when(mockFileHandler.readFile("secret.txt")).thenReturn("XYZ");

        // loadKey returns null in your real cipher class, so we mock that behavior
        when(mockCipher.loadKey("key.txt")).thenReturn(null);

        when(mockCipher.getCipher()).thenReturn("ABC");
        when(mockCipher.decipher("XYZ", "ABC")).thenReturn("DECODED");

        String result = controller.displayFile("1", "key.txt");

        assertEquals("DECODED", result);
        verify(mockCipher).loadKey("key.txt");
        verify(mockCipher).decipher("XYZ", "ABC");
    }

    @Test
    public void testDisplayFile_invalidNumber() {
        List<String> files = Arrays.asList("a.txt");
        when(mockFileHandler.getAvailableFiles()).thenReturn(files);

        String result = controller.displayFile("5", null);

        assertEquals("ERROR: Invalid file number.", result);
    }

    @Test
    public void testDisplayFile_nonNumeric() {
        String result = controller.displayFile("abc", null);

        assertEquals("ERROR: File number must be a number.", result);
    }

    @Test
    public void testDisplayFile_exception() {
        when(mockFileHandler.getAvailableFiles()).thenThrow(new RuntimeException());

        String result = controller.displayFile("1", "key.txt");

        assertEquals("ERROR: Unable to display file.", result);
    }
}
