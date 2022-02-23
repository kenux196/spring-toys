package me.kenux.springsecurity.web;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.kenux.springsecurity.domain.MessageService;
import me.kenux.springsecurity.web.dto.UserResponse;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

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
        return "redirect:main";
    }

    @GetMapping("/main")
    public String welcome(Authentication authentication, Model model) {
        System.out.println("LoginController.welcome");

        final String name = authentication.getName();
        String roles = authentication.getAuthorities().toString();

        log.debug("name = {}, roles = {}", name, roles);

        model.addAttribute("user", new UserResponse(name, roles));

        final String message = messageService.getMessage();
        log.debug("message = {}", message);

        return "main";
    }
}