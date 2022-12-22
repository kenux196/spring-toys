package me.kenux.springsecurity.web;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.kenux.springsecurity.web.dto.UserResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
public class LoginController {

//    @GetMapping("/login")
//    public String login() {
//        log.debug("Get /login");
//        return "login";
//    }
//
////    @ResponseBody
//    @PostMapping("/login")
//    public String loggedIn() {
//        log.debug("POST /login");
//        return "redirect:/";
//    }

//    @GetMapping("/")
//    public String welcome(Model model) {
//        log.debug("Get /");
//
//        String name = authentication().getName();
//        String roles = authentication().getAuthorities().toString();
//        log.debug("name = {}, roles = {}", name, roles);
//        model.addAttribute("user", new UserResponse(name, roles));
//        return "index";
//    }

    private Authentication authentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }
}