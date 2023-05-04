package com.ezyxip.runiwweb;

import com.ezyxip.runiwweb.jpa.User;
import com.ezyxip.runiwweb.jpa.UsersCrud;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.session.MapSession;
import org.springframework.session.Session;
import org.springframework.session.SessionRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.logging.Logger;

@Controller
public class MainController {

    @Autowired
    UsersCrud usersCrud;
    @Autowired
    SessionRepository<MapSession> sess;

    static Logger logger = Logger.getLogger(MainController.class.getName());

    @RequestMapping("/")
    public String mainEndpoint(@CookieValue(value = "sessid", required = false)String sessid){
        if(sessid != null){
            return "redirect:/pa";
        }
        return "index.html";
    }

    @RequestMapping("/auth")
    public String authPage(){
        return "auth.html";
    }

    @RequestMapping("/pa")
    public String paPage(Model model, @CookieValue("sessid") String sessid){
        User user = sess.findById(sessid).getAttribute("info");
        model.addAttribute("username", user.getName());
        model.addAttribute("id", user.getId());
        return "personal-area.html";
    }

    @PostMapping("/auth/check")
    public String authService(
            @RequestParam("password")String password,
            @RequestParam("login")String login,
            HttpServletResponse response
    ){
        Iterable<User> us= usersCrud.findAll();
        for(User i : us){
            if(i.getName().equals(login) && i.getPassword().equals(password)){
                logger.info("Пароль и логин приняты");
                MapSession s = sess.createSession();
                s.setAttribute("info", i);
                Cookie cookie = new Cookie("sessid", s.getId());
                cookie.setPath("/");
                response.addCookie(cookie);
                sess.save(s);
                break;
            }
        }
        return "redirect:../";
    }
}
