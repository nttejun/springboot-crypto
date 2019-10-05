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

    public byte[] addPadding(byte[] prePaddingData) {
        byte[] paddingData = new byte[32];
        System.arraycopy(prePaddingData, 0, paddingData, 0, prePaddingData.length);
        return paddingData;
    }

    public byte[] xorAlgorithm(byte[] targetData, byte[] pbkdf2Key, int dataSize) {
        byte[] xorResult = new byte[dataSize];
        for (int i = 0; i < targetData.length; i++) {
            xorResult[i] = (byte) (targetData[i] ^ pbkdf2Key[i]);
        }
        return xorResult;
    }

    public String convertByteToHex(byte[] dataSize) {
        StringBuilder stringBuilder = new StringBuilder();
        for (byte b : dataSize) {
            stringBuilder.append(String.format("%02x", b));
        }
        return stringBuilder.toString();
    }
}
