package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import service.CryptoService;

@Controller
public class MainController {

    @Autowired
    CryptoService cryptoService;

    @GetMapping(value = "crypto")
    public String demo(Model model) {

        String encryptHexData = cryptoService.plainTextEncrypt();
        model.addAttribute("encryptData", encryptHexData);
        return "response";

    }
}
