package service;

import org.springframework.beans.factory.annotation.Autowired;
import util.Crypto;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public class CryptoService {

    @Autowired
    Crypto crypto;

    public String plainTextEncrypt() {

        try {

            Crypto crypto = new Crypto();
            String password = "passwd";
            byte[] salt = "salt".getBytes("utf-8");
            byte[] DerivedKey = crypto.createPbkdf2Key(password, salt, 1);
            byte[] DerivedKeyPadding = crypto.addPadding(DerivedKey);

            String plainTextStr = "홍길동";
            byte[] plainText = plainTextStr.getBytes("utf-8");
            byte[] plainTextPadding = crypto.addPadding(plainText);

            byte[] encryptXorData = crypto.xorAlgorithm(plainTextPadding, DerivedKeyPadding, 32);
            String hexXorData = crypto.convertByteToHex(encryptXorData);

            return hexXorData;

        } catch (UnsupportedEncodingException | InvalidKeySpecException | NoSuchAlgorithmException e){

            return "ENCRYPT EXCEPTION";

        }
    }
}
