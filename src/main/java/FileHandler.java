import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class FileHandler {
    //defaulted to data, might need to change that
    private static final String DATA_FOLDER = "data";

    //first method
    public List<String> getAvailableFiles() {
        List<String> files = new ArrayList<>();
        File dataDir = new File(DATA_FOLDER);

        if (!dataDir.exists() || !dataDir.isDirectory()) {
            return files; // return empty list
        }

        File[] fileArray = dataDir.listFiles((dir, name) -> name.endsWith(".txt"));
        if (fileArray != null) {
            for (File file : fileArray) {
                files.add(file.getName());
            }
        }

        return files;
    }

    // reads file contnents
    public String readFile(String filename) {
        File dataFile = new File(DATA_FOLDER, filename);
        StringBuilder fileOutput = new StringBuilder();

        try (Scanner myReader = new Scanner(dataFile)) {
            while (myReader.hasNextLine()) {
                fileOutput.append(myReader.nextLine());
                if (myReader.hasNextLine()) {
                    fileOutput.append(System.lineSeparator());
                }
            }
        } catch (FileNotFoundException e) {
            return "Error: File not found - " + filename;
        }

        return fileOutput.toString();
    }
}