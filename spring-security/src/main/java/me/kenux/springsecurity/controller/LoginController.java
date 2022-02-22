package me.kenux.springsecurity.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@Controller
public class LoginController {

    @GetMapping("/login")
    public String login() {
        System.out.println("LoginController.login");
        return "login";
    }

//    @ResponseBody
    @PostMapping("/login")
    public String loggedIn(Authentication authentication) {
        System.out.println("LoginController.loggedIn");
        System.out.println("authentication = " + authentication);
        return "redirect:hello";
    }

    @GetMapping("/hello")
    public String welcome(Authentication authentication, Model model) {
        final String name = authentication.getName();
        model.addAttribute("username", name);
        return "welcome";
    }
}