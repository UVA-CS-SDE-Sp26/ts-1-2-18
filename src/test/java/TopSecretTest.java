import org.junit.jupiter.api.*;
import java.io.*;
import java.nio.file.*;

import static org.junit.jupiter.api.Assertions.*;

public class TopSecretTest {

    //store original streams
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;

    // redirect console output streams
    private ByteArrayOutputStream outContent;
    private ByteArrayOutputStream errContent;

    @BeforeEach
    public void setUp() throws IOException {
        // Capture console output
        outContent = new ByteArrayOutputStream();
        errContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));

        // Create data folder and sample files
        Path dataDir = Paths.get("data");
        if (!Files.exists(dataDir)) Files.createDirectory(dataDir);
        Files.writeString(dataDir.resolve("file1.txt"), "Hello World");
        Files.writeString(dataDir.resolve("file2.txt"), "CipheredText");

        // Create ciphers folder and key.txt
        Path ciphersDir = Paths.get("ciphers");
        if (!Files.exists(ciphersDir)) Files.createDirectory(ciphersDir);
        Files.writeString(ciphersDir.resolve("key.txt"),
                "abcdefghijklmnopqrstuvwxyz\nzyxwvutsrqponmlkjihgfedcba");
    }

    @AfterEach
    public void tearDown() throws IOException {
        //restore original console stream
        System.setOut(originalOut);
        System.setErr(originalErr);

        deleteDirectory(new File("data"));
        deleteDirectory(new File("ciphers"));
    }

    private void deleteDirectory(File dir) {
        if (dir.exists()) {
            for (File file : dir.listFiles()) {
                file.delete();
            }
            dir.delete();
        }
    }

    @Test
    public void testNoArgumentsListsFiles() {
        TopSecret.main(new String[]{});

        String output = outContent.toString().trim();
        assertTrue(output.contains("file1.txt") && output.contains("file2.txt"),
                "Should list files in data folder.");
    }
}