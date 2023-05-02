package com.ezyxip.runiwweb;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.logging.Logger;

@Controller
public class MainStaticController {

    static Logger logger = Logger.getLogger(MainStaticController.class.getName());

    @RequestMapping("/")
    public String mainEndpoint(){

        return "index.html";
    }

    @RequestMapping("/auth")
    public String authPage(){
        return "auth.html";
    }

    @RequestMapping("/pa")
    public String paPage(){
        return "personal-area.html";
    }

    @PostMapping("/auth/check")
    public String authService(@RequestParam("password")String password){
        logger.info("Введённый пароль: " + password);
        return "redirect:../";
    }
}
