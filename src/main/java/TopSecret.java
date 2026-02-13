/**
 * Commmand Line Utility
 */
 import java.io.IOException;
 import java.nio.file.Files;
 import java.nio.file.Path;
 import java.nio.file.Paths;
 
 public class TopSecret {
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
                 Path path = Paths.get("ciphers/key.txt");
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
