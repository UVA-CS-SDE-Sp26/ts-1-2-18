import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileHandler {
    private String filepath;

    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }
    public String readFile(String filepath){
        File data_file=new File(filepath);
        String file_output = "this is a blank output. Something went wrong";
        try (Scanner myReader = new Scanner(data_file)) {
            while (myReader.hasNextLine()) {
                file_output = myReader.nextLine();
                System.out.println(file_output);
            }
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred when finding file.");
            e.printStackTrace();
        }
        return file_output;
    }
}
