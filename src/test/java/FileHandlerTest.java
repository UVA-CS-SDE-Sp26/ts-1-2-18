import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

class FileHandlerTest {
    @TempDir
    Path tempDir;



    @Test
    void getFileList() throws IOException {
        FileHandler fileHandlerInstance = new FileHandler(tempDir.toString());
        //This creates child files in tempDir, then writes their contents
        Path file = tempDir.resolve("01 emptyFile.txt");
        Files.writeString(file, "");
        file = tempDir.resolve("02 fileWithMultipleLines.txt");
        Files.writeString(file, "This is a file \n with multiple lines. \n.txt");
        file = tempDir.resolve("03 fileWithMultipleLinesNotEndingOnANewline.txt");
        Files.writeString(file, "This is a file \n with multiple lines which doesn't end on a newline");
        file = tempDir.resolve("04 fileWithSymbolsAndWordsAfter.txt");
        Files.writeString(file, "This is a file with 1 number, and some punctuation !@#$%^&*() \" ,./?><{}|][ and some words after.");
        file = tempDir.resolve("05 fileEndingWithSymbols.txt");
        Files.writeString(file, "This is a file with 1 number, and some punctuation !@#$%^&*()");

        assertEquals( "01 emptyFile.txt\n" +
                "02 fileWithMultipleLines.txt\n" +
                "03 fileWithMultipleLinesNotEndingOnANewline.txt\n" +
                "04 fileWithSymbolsAndWordsAfter.txt\n" +
                "05 fileEndingWithSymbols.txt",fileHandlerInstance.getFileList());
    }

    @Test
    void doesFileExist() throws IOException {
        FileHandler fileHandlerInstance = new FileHandler(tempDir.toString());

        //This creates child files in tempDir, then writes their contents
        Path file = tempDir.resolve("01 emptyFile.txt");
        Files.writeString(file, "");
        file = tempDir.resolve("02 fileWithMultipleLines.txt");
        Files.writeString(file, "This is a file \n with multiple lines. \n.txt");
        file = tempDir.resolve("03 fileWithMultipleLinesNotEndingOnANewline.txt");
        Files.writeString(file, "This is a file \n with multiple lines which doesn't end on a newline");
        file = tempDir.resolve("04 fileWithSymbolsAndWordsAfter.txt");
        Files.writeString(file, "This is a file with 1 number, and some punctuation !@#$%^&*() \" ,./?><{}|][ and some words after.");
        file = tempDir.resolve("05 fileEndingWithSymbols.txt");
        Files.writeString(file, "This is a file with 1 number, and some punctuation !@#$%^&*()");


        assertTrue(fileHandlerInstance.doesFileExist("01 emptyFile.txt"));
        assertTrue(fileHandlerInstance.doesFileExist("02 fileWithMultipleLines.txt"));
        assertTrue(fileHandlerInstance.doesFileExist("03 fileWithMultipleLinesNotEndingOnANewline.txt"));
        assertTrue(fileHandlerInstance.doesFileExist("04 fileWithSymbolsAndWordsAfter.txt"));
        assertTrue(fileHandlerInstance.doesFileExist("05 fileEndingWithSymbols.txt"));

        //Asserts that this call will cause a runtime exception. () -> is necessary so the test doesn't crash instantly.
        assertThrows(RuntimeException.class, () -> fileHandlerInstance.doesFileExist("06 fileThatDoesn'tExist.txt"));
    }

    @Test
    void readFile() throws IOException {
        FileHandler fileHandlerInstance = new FileHandler(tempDir.toString());

        //This creates child files in tempDir, then writes their contents
        Path file = tempDir.resolve("01 emptyFile.txt");
        Files.writeString(file, "");
        file = tempDir.resolve("02 fileWithMultipleLines.txt");
        Files.writeString(file, "This is a file \n with multiple lines. \n.txt");
        file = tempDir.resolve("03 fileWithMultipleLinesNotEndingOnANewline.txt");
        Files.writeString(file, "This is a file \n with multiple lines which doesn't end on a newline");
        file = tempDir.resolve("04 fileWithSymbolsAndWordsAfter.txt");
        Files.writeString(file, "This is a file with 1 number, and some punctuation !@#$%^&*() \" ,./?><{}|][ and some words after.");
        file = tempDir.resolve("05 fileEndingWithSymbols.txt");
        Files.writeString(file, "This is a file with 1 number, and some punctuation !@#$%^&*()");


        assertEquals("", fileHandlerInstance.readFile("01 emptyFile.txt"));
        assertEquals("This is a file \n with multiple lines. \n.txt", fileHandlerInstance.readFile("02 fileWithMultipleLines.txt"));
        assertEquals("This is a file \n with multiple lines which doesn't end on a newline", fileHandlerInstance.readFile("03 fileWithMultipleLinesNotEndingOnANewLine.txt"));


    }
}