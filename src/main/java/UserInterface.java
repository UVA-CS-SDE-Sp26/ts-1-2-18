import java.io.IOException;

public class UserInterface {

    public static void main(String[] args) {
        // Initialize FileHandler
        FileHandler fileHandler = new FileHandler();
        // Initialize ProgramControl with the FileHandler
        ProgramControl programControl = new ProgramControl(fileHandler);

        try {
            // Pass command-line arguments to ProgramControl
            String output = programControl.handleRequest(args);
            // Display result
            System.out.println(output);
        } catch (IOException e) {
            System.err.println("An error occurred while accessing files: " + e.getMessage());
        } catch (RuntimeException e) {
            System.err.println("Runtime error: " + e.getMessage());
        }
    }
}