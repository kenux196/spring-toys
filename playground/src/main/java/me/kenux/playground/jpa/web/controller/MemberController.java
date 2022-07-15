package me.kenux.playground.jpa.web.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.kenux.playground.jpa.domain.Member;
import me.kenux.playground.jpa.service.MemberService;
import me.kenux.playground.jpa.web.dto.MemberBasicResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;

    @PostMapping
    public ResponseEntity<?> join() {

        final Long id = memberService.join(new Member("newMember"));
        return ResponseEntity.ok(id);
    }

    @GetMapping
    public ResponseEntity<?> getAllMember() {
        final List<MemberBasicResponse> memberBasicResponses = memberService.getMembers().stream()
            .map(MemberBasicResponse::new)
            .collect(Collectors.toList());
        return ResponseEntity.ok(memberBasicResponses);
    }

    @GetMapping("/osiv-off")
    public ResponseEntity<?> getAllMember1() {
        final List<Member> members = memberService.getMembers();
        return ResponseEntity.ok(members);
    }
}
