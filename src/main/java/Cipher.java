import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Cipher {
    private String key;
    private String ciphered_string;

    public Cipher(String key, String ciphered_string){
        this.key = key;
        this.ciphered_string = ciphered_string;
    }

    public String decipher(){
        return process(this.key);
    }

    public String decipher(String keyPath) {
        try {
            String fileContent = Files.readString(Paths.get(keyPath));
            return process(fileContent);
        } catch (IOException e) {
            return this.ciphered_string;
        }
    }

    private String process(String keyContent){
        // check for null values first
        if (keyContent == null || this.ciphered_string == null) {
            return this.ciphered_string != null ? this.ciphered_string : "";
        }

        String[] parts = keyContent.split("\\R");
        // check for two lines in the key file
        if (parts.length < 2) {
            return this.ciphered_string;
        }

        // initialize the mapping strings and the resulting builder
        String plainMap = parts[0];
        String cipherMap = parts[1];
        StringBuilder result = new StringBuilder();


        for (char c : this.ciphered_string.toCharArray()) {
            // to cover for different capitalization
            char lowerC = Character.toLowerCase(c);
            // find the index in the cipher map
            int index = cipherMap.toLowerCase().indexOf(lowerC);

            // if the index is in the bounds of the plain map
            if (index != -1 && index < plainMap.length()) {
                // map it from cipher to original map
                char replacement = plainMap.charAt(index);

                // and replace it with the original
                // handling for diff capitalization
                if (Character.isUpperCase(c)) {
                    result.append(Character.toUpperCase(replacement));
                } else {
                    result.append(Character.toLowerCase(replacement));
                }
            }
            // otherwise just append the character if not in the cipher map
            else {
                result.append(c);
            }
        }
        return result.toString();
    }

    // getters and setters
    public String getCipheredString(){
        return this.ciphered_string;
    }
    public String getKey(){
        return this.key;
    }
    public void setCipheredString(String ciphered_string){
        this.ciphered_string = ciphered_string;
    }
    public void setKey(String key){
        this.key = key;
    }
}