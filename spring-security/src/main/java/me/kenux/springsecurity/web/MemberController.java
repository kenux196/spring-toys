package me.kenux.springsecurity.web;

import me.kenux.springsecurity.domain.member.Member;
import me.kenux.springsecurity.domain.member.Role;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class MemberController {


    @GetMapping("/members")
    public ResponseEntity<?> members(Authentication authentication) {
        System.out.println("authentication.getName() = " + authentication.getName());
        final Member userA = new Member("userA", "password", Role.USER);
        userA.setId(1L);
        final List<Member> members = List.of(userA);
        return ResponseEntity.ok(members);
    }
}