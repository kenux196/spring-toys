package me.kenux.springsecurity.web;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.kenux.springsecurity.domain.MessageService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@Controller
@RequiredArgsConstructor
public class LoginController {

    private final MessageService messageService;

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
        messageService.getMessage();
        return "redirect:hello";
    }

    @GetMapping("/hello")
    public String welcome(Authentication authentication, Model model) {
        System.out.println("LoginController.welcome");
        final String name = authentication.getName();
        model.addAttribute("username", name);
        final String message = messageService.getMessage();
        log.debug("message = {}", message);
        return "welcome";
    }
}