import java.util.List;

public class ProgramController {

    private final FileHandler fileHandler;
    private final Cipher cipher;

    // Constructor: create the objects from Role B and Role D
    public ProgramController() {
        this.fileHandler = new FileHandler();  // Role B
        this.cipher = new Cipher();            // Role D
    }

    // LIST FILES
    public String listFiles() {
        try {
            List<String> files = fileHandler.getAvailableFiles();

            if (files == null || files.isEmpty()) {
                return "No files found.";
            }

            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < files.size(); i++) {
                sb.append(String.format("%02d %s", i + 1, files.get(i)));
                if (i < files.size() - 1) {
                    sb.append(System.lineSeparator());
                }
            }
            return sb.toString();

        } catch (Exception e) {
            return "ERROR: Unable to list files.";
        }
    }

    // Display the contents of a file, deciphered if a key is provided
    public String displayFile(String fileNumberString, String keyPath) {
        try {
            // Convert file number
            int fileNumber = Integer.parseInt(fileNumberString);

            // Get list of files
            List<String> files = fileHandler.getAvailableFiles();

            if (fileNumber < 1 || fileNumber > files.size()) {
                return "ERROR: Invalid file number.";
            }

            // Get the filename
            String filename = files.get(fileNumber - 1);

            // Read raw contents
            String rawText = fileHandler.readFile(filename);

            // If no key provided, return raw text
            if (keyPath == null || keyPath.isBlank()) {
                return rawText;
            }

            // Load key and decipher
            CipherKey key = cipher.loadKey(keyPath);
            String deciphered = cipher.decipher(rawText, key);

            return deciphered;

        } catch (NumberFormatException e) {
            return "ERROR: File number must be a number.";
        } catch (Exception e) {
            return "ERROR: Unable to display file.";
        }
    }
}
