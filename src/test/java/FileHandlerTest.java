import org.junit.jupiter.api.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class FileHandlerTest {

    private static final String DATA_FOLDER = "data";
    private File dataDir;

    @BeforeEach
    void setUp() {
        dataDir = new File(DATA_FOLDER);

        // Ensure a clean test directory
        if (dataDir.exists()) {
            for (File f : dataDir.listFiles()) {
                f.delete();
            }
            dataDir.delete();
        }

        dataDir.mkdir();
    }

    @AfterEach
    void tearDown() {
        // Clean up after each test
        if (dataDir.exists()) {
            for (File f : dataDir.listFiles()) {
                f.delete();
            }
            dataDir.delete();
        }
    }

    @Test
    void testGetAvailableFiles_NoTxtFiles() {
        FileHandler handler = new FileHandler();

        List<String> files = handler.getAvailableFiles();

        assertTrue(files.isEmpty());
    }

    @Test
    void testGetAvailableFiles_ReturnsOnlyTxtFiles() throws IOException {
        new File(dataDir, "a.txt").createNewFile();
        new File(dataDir, "b.txt").createNewFile();
        new File(dataDir, "ignore.pdf").createNewFile();

        FileHandler handler = new FileHandler();

        List<String> files = handler.getAvailableFiles();

        assertEquals(2, files.size());
        assertTrue(files.contains("a.txt"));
        assertTrue(files.contains("b.txt"));
    }

    @Test
    void testReadFile_Success() throws IOException {
        File file = new File(dataDir, "test.txt");
        Files.writeString(file.toPath(), "Hello\nWorld");

        FileHandler handler = new FileHandler();

        String result = handler.readFile("test.txt");

        assertEquals("Hello" + System.lineSeparator() + "World", result);
    }

    @Test
    void testReadFile_FileNotFound() {
        FileHandler handler = new FileHandler();

        String result = handler.readFile("missing.txt");

        assertEquals("Error: File not found - missing.txt", result);
    }
}