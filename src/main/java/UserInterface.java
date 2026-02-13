import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class UserInterface {

    private FileHandler fileHandler;

    // Default constructor
    public UserInterface() {
        this.fileHandler = new FileHandler();
    }

    //testing constructor
    public UserInterface(FileHandler fileHandler) {
        this.fileHandler = fileHandler;
    }

    public void start(String[] args) {

        try {

            // Case 0: No arguments so list all available files
            if (args.length == 0) {
                String fileList = fileHandler.getFileList();
                String[] files = fileList.split("\n");
                System.out.println("Available Files:");
                for (int i = 0; i < files.length; i++) {
                    System.out.printf("%02d %s%n", i + 1, files[i]);
                }
                return;
            }

            // Case 1: One argument given by number
            if (args.length == 1) {
                if (!isNumeric(args[0])) {
                    System.out.println("Error: File number must be numeric.");
                    return;
                }
                displayFileContents(args[0], null);
                return;
            }

            // Case 2: Two arguments: number and cipher key file
            if (args.length == 2) {
                if (!isNumeric(args[0])) {
                    System.out.println("Error: File number must be numeric.");
                    return;
                }
                displayFileContents(args[0], args[1]);
                return;
            }

            // Too many arguments
            System.out.println("Error: Invalid number of arguments.");
            printUsage();

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    //check if a string is numeric
    private boolean isNumeric(String value) {
        return value != null && value.matches("\\d+");
    }

    // Print usage instructions
    private void printUsage() {
        System.out.println("Usage:");
        System.out.println("  java topsecret");
        System.out.println("  java topsecret <fileNumber>");
        System.out.println("  java topsecret <fileNumber> <cipherKey>");
    }

    // Display file contents and decipher if needed
    private void displayFileContents(String fileNumber, String keyFile) throws IOException {

        // Map file number to file name
        String fileList = fileHandler.getFileList();
        String[] files = fileList.split("\n");
        int index = Integer.parseInt(fileNumber) - 1;

        if (index < 0 || index >= files.length) {
            System.out.println("Error: File number out of range.");
            return;
        }

        String fileName = files[index];
        String contents = fileHandler.readFile(fileName);

        // Only decipher if a key file is provided
        if (keyFile != null) {
            String key = Files.readString(Paths.get(keyFile));
            Cipher cipher = new Cipher(key, contents);
            contents = cipher.decipher();
        }

        System.out.println(contents);
    }
}