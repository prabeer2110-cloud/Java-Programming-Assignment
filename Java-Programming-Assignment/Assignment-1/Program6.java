// 1. Abstract Base Class
abstract class Cipher {
    
    // Abstract methods: No body, must be overridden by subclasses
    public abstract String encrypt(String text);
    public abstract String decrypt(String text);
    
    // Concrete method: Shared logic available to all subclasses
    public void displayStatus(String cipherName, String original, String encrypted, String decrypted) {
        System.out.println("--- " + cipherName + " ---");
        System.out.println("Original:  " + original);
        System.out.println("Encrypted: " + encrypted);
        System.out.println("Decrypted: " + decrypted);
        System.out.println("------------------------\n");
    }
}

// 2. Derived Class: Caesar Cipher (Shifts letters by a specific number)
class CaesarCipher extends Cipher {
    private int shift;

    public CaesarCipher(int shift) {
        this.shift = shift;
    }

    @Override
    public String encrypt(String text) {
        return shiftText(text, this.shift);
    }

    @Override
    public String decrypt(String text) {
        return shiftText(text, -this.shift); // Decrypt by shifting backwards
    }

    // Helper method to handle the shifting logic
    private String shiftText(String text, int shiftAmount) {
        StringBuilder result = new StringBuilder();
        
        for (char character : text.toCharArray()) {
            if (Character.isLetter(character)) {
                char base = Character.isLowerCase(character) ? 'a' : 'A';
                // Calculate the new character position (handling wrap-around and negative shifts)
                int offset = (character - base + shiftAmount) % 26;
                if (offset < 0) {
                    offset += 26;
                }
                result.append((char) (base + offset));
            } else {
                // Keep spaces and punctuation as they are
                result.append(character);
            }
        }
        return result.toString();
    }
}

// 3. Derived Class: Reverse Cipher (Simply reverses the string)
class ReverseCipher extends Cipher {

    @Override
    public String encrypt(String text) {
        // Reversing a string using StringBuilder
        return new StringBuilder(text).reverse().toString();
    }

    @Override
    public String decrypt(String text) {
        // Decrypting a reversed string is just reversing it again
        return new StringBuilder(text).reverse().toString();
    }
}

// 4. Main Class to demonstrate the system
public class CipherSystemDemo {
    public static void main(String[] args) {
        String message = "Hello Java World!";

        // Instantiate the subclasses using the Abstract Class reference
        Cipher caesar = new CaesarCipher(3); // Shift by 3
        Cipher reverse = new ReverseCipher();

        // Perform Caesar Cipher operations
        String caesarEncrypted = caesar.encrypt(message);
        String caesarDecrypted = caesar.decrypt(caesarEncrypted);
        caesar.displayStatus("Caesar Cipher (Shift 3)", message, caesarEncrypted, caesarDecrypted);

        // Perform Reverse Cipher operations
        String reverseEncrypted = reverse.encrypt(message);
        String reverseDecrypted = reverse.decrypt(reverseEncrypted);
        reverse.displayStatus("Reverse Cipher", message, reverseEncrypted, reverseDecrypted);
    }
}
