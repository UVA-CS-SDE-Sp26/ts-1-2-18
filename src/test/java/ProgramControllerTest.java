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

        controller = new ProgramController(mockFileHandler, mockCipher);
    }

    // Be able to attain the files through FileHandler
    @Test
    public void testGetFilesFromFileHandler() {
        List<String> files = Arrays.asList("a.txt", "b.txt");
        when(mockFileHandler.getAvailableFiles()).thenReturn(files);

        String result = controller.listFiles();

        assertTrue(result.contains("a.txt"));
        assertTrue(result.contains("b.txt"));
        verify(mockFileHandler, times(1)).getAvailableFiles();
    }

    // Be able to display the array of file names
    @Test
    public void testListFilesFormatted() {
        List<String> files = Arrays.asList("a.txt", "b.txt", "c.txt");
        when(mockFileHandler.getAvailableFiles()).thenReturn(files);

        String result = controller.listFiles();

        assertEquals("01 a.txt\n02 b.txt\n03 c.txt", result);
    }

    // Test for empty files
    @Test
    public void testListFilesEmpty() {
        when(mockFileHandler.getAvailableFiles()).thenReturn(List.of());

        String result = controller.listFiles();

        assertEquals("No files found.", result);
    }

    // Be able to print/read the text files
    @Test
    public void testDisplayFilePlainText() {
        when(mockFileHandler.getAvailableFiles()).thenReturn(List.of("hello.txt"));
        when(mockFileHandler.readFile("hello.txt")).thenReturn("HELLO WORLD");

        String result = controller.displayFile("1", "");

        assertEquals("HELLO WORLD", result);
        verify(mockFileHandler).readFile("hello.txt");
        verify(mockCipher, never()).decipher(anyString(), anyString());
    }


    // Be able to print/read the text files with key
    @Test
    public void testDisplayFileWithKey() {
        when(mockFileHandler.getAvailableFiles()).thenReturn(List.of("secret.txt"));
        when(mockFileHandler.readFile("secret.txt")).thenReturn("XYZ");

        when(mockCipher.loadKey("key.txt")).thenReturn(null);

        when(mockCipher.getCipher()).thenReturn("ABC");
        when(mockCipher.decipher("XYZ", "ABC")).thenReturn("DECODED");

        String result = controller.displayFile("1", "key.txt");

        assertEquals("DECODED", result);
        verify(mockCipher).loadKey("key.txt");
        verify(mockCipher).decipher("XYZ", "ABC");
    }

    // Be able to check valid user inputs and catch errors
    @Test
    public void testInvalidFileNumber() {
        when(mockFileHandler.getAvailableFiles()).thenReturn(List.of("a.txt"));

        String result = controller.displayFile("5", null);

        assertEquals("ERROR: Invalid file number.", result);
    }

    // Test is user input is numerical
    @Test
    public void testNonNumericInput() {
        String result = controller.displayFile("abc", null);

        assertEquals("ERROR: File number must be a number.", result);
    }

    // Test DisplayFile exception
    @Test
    public void testDisplayFileException() {
        when(mockFileHandler.getAvailableFiles()).thenThrow(new RuntimeException());

        String result = controller.displayFile("1", "key.txt");

        assertEquals("ERROR: Unable to display file.", result);
    }
}
