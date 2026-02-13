import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class TopSecret {
    public static void main(String[] args) {

        File dataFolder = new File("data");
        File[] files = dataFolder.listFiles();

        if (files == null || files.length == 0) {
            System.err.println("No files found in data folder");
            return;
        }

        if (args.length == 0) {
            for (int i = 0; i < files.length; i++) {
                System.out.printf("%02d %s%n", i + 1, files[i].getName());
            }
            return;
        }

        //argument provided

        int index;
        try {
            index = Integer.parseInt(args[0]) -1;}
        catch (NumberFormatException e) {
            System.err.println("Invalid file number");
            return;
        }

        if (index < 0 || index >= files.length) {
            System.err.println("Invalid file number");
            return;
        }

        try{
            //read file content
            String fileContent = Files.readString(files[index].toPath());

            String input = args[0];

            String keyFileName = (args.length >= 2) ? args[1] : "key.txt";

            Cipher cipher = new Cipher(input);

            System.out.println(cipher.decipher(keyFileName));
        }
        catch (IOException e) {
            System.err.println("File not found");
        }
        catch (Exception e) {
            System.err.println("Invalid cipher key");
        }

    }
}