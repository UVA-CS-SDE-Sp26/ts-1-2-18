public class Cipher {
    private String key; 
    private String ciphered_string;
    
    public Cipher(String key, String ciphered_string){
        this.key = key;
        this.ciphered_string = ciphered_string;
    }
    
    public String decipher (){
        return this.ciphered_string;
    }
    public String decipher (String arg){
        return this.ciphered_string;
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