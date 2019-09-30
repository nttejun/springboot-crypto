package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import util.Crypto;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

@Controller
public class MainController {

    @Autowired
    Crypto crypto;

    @GetMapping(value = "pbkdf")
    public String demo(Model model) throws UnsupportedEncodingException, InvalidKeySpecException, NoSuchAlgorithmException {

        Crypto pbkdf2 = new Crypto();
        String password = "passwd";
        byte[] salt = "salt".getBytes("utf-8");

        byte[] DK = pbkdf2.createPbkdf2Key(password, salt, 1);
        byte[] DK32 = new byte[32];
        System.arraycopy(DK, 0, DK32, 0, DK.length);

        String plainTextStr = "passwd";
        byte[] plainText = plainTextStr.getBytes("utf-8");
        byte[] plainText32 = new byte[32];
        System.arraycopy(plainText, 0, plainText32, 0, plainText.length);

        byte[] pbkdfEncrypt = DK;
        byte[] xorEncrypt = pbkdf2.encryptXorAlgorithm(plainText32, DK, 32);
        String xorHexData = pbkdf2.convertByteToHex(xorEncrypt);
        System.out.println("pbkdfEncrypt >>> " + pbkdf2.convertByteToHex(pbkdfEncrypt));
        System.out.println("xorEncrypt >>> " + pbkdf2.convertByteToHex(xorEncrypt));
        System.out.println("xorHexData >>> " + xorHexData);
        model.addAttribute("testdata", xorHexData);

        return "response";
    }
}
