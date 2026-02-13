import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class ProgramControl {
    private FileHandler fileHandler;

    public  ProgramControl(FileHandler fileHandler){
        this.fileHandler = fileHandler;
    }


    public String handleRequest(String[] args) throws IOException {
        String rawFileList= fileHandler.getFileList();

        if (rawFileList == null || rawFileList.isEmpty()) {
            return "No data files found in the directory.";
        }
        ArrayList<String> files = new ArrayList<>(Arrays.asList(rawFileList.split("\n")));

        if( args.length == 0 ){
            return formatFileList(files);
        }

        String firstArg = args[0];
        for(int i = 0; i < firstArg.length(); i++){
            if(!Character.isDigit(firstArg.charAt(i))){
                return "Please enter a valid file number";
            }
        }

        int index = Integer.parseInt(firstArg)-1;
        if (index < 0 || index >= files.size()){
            return "File index " + (args[0]) + " is out of range.";

        }
        String filename = files.get(index);
        String cipheredText = fileHandler.readFile(filename);


        if( args.length == 1 ){
            Cipher cipher = new Cipher(cipheredText, fileHandler.readFile("key.txt"));
            return cipher.decipher();
        } else{
            Cipher cipher = new Cipher(cipheredText, fileHandler.readFile(args[1]));
            return cipher.decipher();
        }
    }

    public String formatFileList(ArrayList<String> files){
        String result = "";
        for(int i = 0; i < files.size(); i++){
            int fileNum = i+1;
            String pre = "";
            if (fileNum <10){
                pre = "0";
            }
            result += pre + fileNum + " " + files.get(i) + "\n";
        }
        return result.trim();

    }

}
