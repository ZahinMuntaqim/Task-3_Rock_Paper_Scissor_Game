import java.security.SecureRandom;

public class KeyGenerator {
    public static byte[] generateKey() {
        SecureRandom random = new SecureRandom();
        byte[] key = new byte[32]; // 256 bits
        random.nextBytes(key);
        return key;
    }
}
