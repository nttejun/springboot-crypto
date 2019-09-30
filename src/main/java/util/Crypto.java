package util;

import org.springframework.stereotype.Component;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

@Component
public class Crypto {

    public byte[] createPbkdf2Key(String password, byte[] salt, int count) throws NoSuchAlgorithmException, InvalidKeySpecException {
        PBEKeySpec pbeKeySpec = new PBEKeySpec(password.toCharArray(), salt, count, 32 * 8);
        SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
        return secretKeyFactory.generateSecret(pbeKeySpec).getEncoded();
    }

    public byte[] encryptXorAlgorithm(byte[] encryptData, byte[] pbkdf2Key, int dataSize) {
        byte[] decryptData = new byte[dataSize];
        for (int i = 0; i < encryptData.length; i++) {
            decryptData[i] = (byte) (encryptData[i] ^ pbkdf2Key[i]);
        }
        return decryptData;
    }

    public String convertByteToHex(byte[] dataSize) {
        StringBuilder stringBuilder = new StringBuilder();
        for (byte b : dataSize) {
            stringBuilder.append(String.format("%02x", b));
        }
        return stringBuilder.toString();
    }
}
