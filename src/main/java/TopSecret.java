public class TopSecret {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.err.println("Usage Error: No arguments provided.");
            System.err.println("Required: java TopSecret <ciphered_text> [optional_key_file]");
            System.err.println("Example:  java TopSecret 01 anotherkey.txt");
            return;
        }

        String input = args[0];

        String keyFileName = (args.length >= 2) ? args[1] : "key.txt";

        Cipher cipher = new Cipher(input);

        System.out.println(cipher.decipher(keyFileName));
    }
}
