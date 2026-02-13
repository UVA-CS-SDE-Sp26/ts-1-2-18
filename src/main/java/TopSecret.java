import java.io.IOException;

public class TopSecret {
    public static void main(String[] args) {
        FileHandler fileHandler = new FileHandler();
        ProgramControl programControl = new ProgramControl(fileHandler);

        try {
            String output = programControl.handleRequest(args);
            System.out.println(output);
        } catch (IOException e) {
            System.err.println("File System Error: " + e.getMessage());
        } catch (RuntimeException e) {
            System.err.println("Execution Error: " + e.getMessage());
        }
    }
}