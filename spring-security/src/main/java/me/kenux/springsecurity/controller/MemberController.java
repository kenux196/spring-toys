package me.kenux.springsecurity.controller;

import me.kenux.springsecurity.domain.Member;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class MemberController {


    @GetMapping("/members")
    public ResponseEntity<?> members(Authentication authentication) {
        System.out.println("authentication.getName() = " + authentication.getName());
        final Member userA = new Member("userA", 20);
        userA.setId(1L);
        final List<Member> members = List.of(userA);
        return ResponseEntity.ok(members);
    }

    @GetMapping("/whoami")
    public String me(Authentication authentication, Model model) {
        model.addAttribute("username", authentication.getName());
        return "me";
    }

}