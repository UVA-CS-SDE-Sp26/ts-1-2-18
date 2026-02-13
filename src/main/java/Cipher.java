public class Cipher {
    /*
    import java.io.IOException;
    import java.nio.file.Files;
    import java.nio.file.Path;
    import java.nio.file.Paths;
    
    public class Main {
        public static void main(String[] args) {
            if (args.length == 0) {
                return;
            }
    
            String cipheredString = args[0];
            String key = "";
    
            if (args.length >= 2) {
                key = args[1];
            } else {
                try {
                    Path path = Paths.get("cipher/key.txt");
                    key = Files.readString(path);
                } catch (IOException e) {
                    System.err.println("Error reading key file: " + e.getMessage());
                    System.exit(1);
                }
            }
    
            Cipher cipher = new Cipher(key, cipheredString);
            System.out.println(cipher.decipher());
        }
    }
    */
    
    private String key; 
    private String ciphered_string;
    
    public Cipher(String key, String ciphered_string){
        this.key = key;
        this.ciphered_string = ciphered_string;
    }
    
    public String decipher(){
        return process(this.key);
    }
    
    public String decipher(String key){
        return process(key);
    }
    
    private String process(String keyToUse){
        if (keyToUse == null || this.ciphered_string == null) {
            return this.ciphered_string;
        }
        
        String[] parts = keyToUse.split("\\R");
        if (parts.length < 2) {
            return this.ciphered_string;
        }
        
        String plain = parts[0];
        String cipher = parts[1];
        StringBuilder result = new StringBuilder();
        
        for (char c : this.ciphered_string.toCharArray()) {
            int index = cipher.indexOf(c);
            if (index != -1 && index < plain.length()) {
                result.append(plain.charAt(index));
            } else {
                result.append(c);
            }
        }
        return result.toString();
    }
    
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