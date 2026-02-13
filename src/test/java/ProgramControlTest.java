import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.*;


class ProgramControlTest {
    private ProgramControl programControl;
    private FileHandler fileHandler;


    /*
    @BeforeEach
   public void setUp() {
    fileHandler = mock(FileHandler.class);
    programControl = new ProgramControl(fileHandler);
    }

    @Test
    void handleRequestsNoArguments() throws IOException {
        when(fileHandler.getFileList()).thenReturn("filea.txt\nfileb.txt");
        String result = programControl.handleRequest(new String[]{});
        assertNotNull(result);
        assertTrue(result.contains("01 filea.txt"));
        assertTrue(result.contains("02 fileb.txt"));
    }



    @Test
    void handleRequestsOneArgument() throws IOException {
        when(fileHandler.getFileList()).thenReturn("filea.txt");
        when(fileHandler.readFile("filea.txt")).thenReturn("message");
        String result = programControl.handleRequest(new String[]{"01"});
        assertEquals("message", result);

    }

    @Test
    void handleRequestsTwoArguments() throws IOException {
        when(fileHandler.getFileList()).thenReturn("filea.txt");
        when(fileHandler.readFile("filea.txt")).thenReturn("message");
        String result = programControl.handleRequest(new String[]{"01", "alt_key.txt"});
        assertNotNull(result);
        assertEquals("message", result);

    }

    @Test
    void invalidArgument() throws IOException {
        when(fileHandler.getFileList()).thenReturn("filea.txt");
        String result = programControl.handleRequest(new String[]{"bcde"});
        assertEquals("Please enter a valid file number", result);

    }

    @Test
    void indexOutOfRange() throws IOException {
        when(fileHandler.getFileList()).thenReturn("filea.txt");
        String result = programControl.handleRequest(new String[]{"60"});
        assertEquals("File index 60 is out of range.", result);

    }
    */
}