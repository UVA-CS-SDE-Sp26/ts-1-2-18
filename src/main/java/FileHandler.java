/*
Handles reading of files.
 */
import java.io.*;
import java.util.stream.*;
import java.nio.file.*;

public class FileHandler {

    final static String fileDirectory = "data" + File.separator;
    String targetFileName;

    public FileHandler() {

    }

    public String getFileList() throws IOException     {
        Path path = Paths.get(fileDirectory);

        try(Stream<Path> stream = Files.list(path)) {
            return stream
                    .filter(Files::isRegularFile)
                    .map(p -> p.getFileName().toString())
                    .collect(Collectors.joining("\n"));
        }
    }

    public boolean doesFileExist(String fileName) {
        Path targetFilePath = Paths.get(fileDirectory+fileName);

        if(Files.exists(targetFilePath)) {
            this.targetFileName = fileDirectory+fileName;
            return true;
        } else {
            throw new RuntimeException("File not found.");
        }
    }

    public String readFile(String fileName) {
        //verify the file exists first
        doesFileExist(fileName);

        String returnString = "";
        File targetFile = new File(fileDirectory+fileName);
        try(FileReader targetFileReader = new FileReader(targetFile)) {
            BufferedReader bufferedTextFileReader = new BufferedReader(targetFileReader);
            String line = "";
            while ((line = bufferedTextFileReader.readLine()) != null) {
                returnString += line;
                returnString += "\n";
            }

        }catch(IOException e) {
                System.out.println("File could not be read."+e.getMessage());
            }
            return returnString;
    }
}