/*
Handles reading of files.
 */

public class FileHandler {

    final static String fileDirectory;
    String targetFileName;

    public FileHandler {
        final static String fileDirectory = "data" + File.separator;
    }

    public String getFileList() throws IOException e     {
        Path path = Paths.get(fileDirectory);

        try(Stream<Path> stream = Files.list(path)) {
            return stream
                    .filter(Files::isRegularFile)
                    .map(p -> p.getFileName().toString())
                    .collect(Collectors.joining("\n"));
        }
    }

    public boolean doesFileExist(String fileName) {
        Path targetFilePath = Paths.get(filesFolder+targetFileName);

        if(Files.exists(targetFilePath)) {
            this.targetFileName = target
        } else {
            throw new RuntimeException("File not found.");
        }
    }

    public String readFile(String fileName) {
        //verify the file exists first
        doesFileExist(fileName);

        String returnString = "";
        File targetFile = new File(fileDirectory+targetFileName);
        try(FileReader targetFileReader = new FileReader(targetFile)) {
            BufferedReader bufferedTextFileReader = new BufferedReader(targetFileReader);
            String line = "";
            while((line=bufferedTextFileReader.readLine())!=null) {
                returnString+=line;
                returnString+="\n";
            } catch(IOException e) {
                System.out.println("File could not be read."+e.getMessage());
            }
            return returnString;
        }
    }
}