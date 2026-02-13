import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class ProgramControl {
    private FileHandler fileHandler;

    public ProgramControl(FileHandler fileHandler){
        this.fileHandler = fileHandler;
    }

    public String handleRequest(String[] args) throws IOException {
        String rawFileList = fileHandler.getFileList();

        if (rawFileList == null || rawFileList.isEmpty()) {
            return "Error: No data files found in the 'data/' directory.";
        }
        ArrayList<String> files = new ArrayList<>(Arrays.asList(rawFileList.split("\n")));

        if (args.length == 0) {
            return formatFileList(files);
        }

        if (args.length > 2) {
            return "Usage Error: Too many arguments.\nUsage: java TopSecret <file_number> [key_filename]";
        }

        int index;
        try {
            index = Integer.parseInt(args[0]) - 1;
        } catch (NumberFormatException e) {
            return "Usage Error: '" + args[0] + "' is not a valid number.\nUsage: java TopSecret <file_number> [key_filename]";
        }

        if (index < 0 || index >= files.size()) {
            return "Index Error: File number " + args[0] + " does not exist.\nPlease choose a number between 01 and " + String.format("%02d", files.size()) + ".";
        }

        String filename = files.get(index);
        String cipheredText = fileHandler.readFile(filename);

        Cipher cipher = new Cipher(cipheredText);

        if (args.length == 1) {
            return cipher.decipher("key.txt");
        } else {
            return cipher.decipher(args[1]);
        }
    }

    public String formatFileList(ArrayList<String> files) {
        StringBuilder result = new StringBuilder("Available Files:\n");
        for (int i = 0; i < files.size(); i++) {
            result.append(String.format("%02d %s\n", i + 1, files.get(i)));
        }
        return result.toString().trim();
    }
}