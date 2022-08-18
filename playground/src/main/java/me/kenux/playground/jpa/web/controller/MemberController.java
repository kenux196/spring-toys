package me.kenux.playground.jpa.web.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.kenux.playground.jpa.domain.Member;
import me.kenux.playground.jpa.service.MemberService;
import me.kenux.playground.jpa.web.dto.CreateMemberRequest;
import me.kenux.playground.jpa.web.dto.MemberBasicResponse;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.*;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;

    @PostMapping
    public ResponseEntity<?> join(@RequestBody CreateMemberRequest request) {

        log.info("requestTime={}", request.getCreatedDate());
        log.info("requestTime.ZoneOffset={}", request.getCreatedDate().getOffset());
        final LocalDateTime now = request.getCreatedDate().toLocalDateTime();
        final ZonedDateTime zone = request.getCreatedDate().toZonedDateTime();
        final Member member = Member.builder()
            .name(request.getName())
            .localCreatedDate(now)
            .offsetCreatedDate(request.getCreatedDate())
            .zonedCreatedDate(zone)
            .build();
        final Long id = memberService.join(member);
        return ResponseEntity.ok(id);
    }

    @GetMapping
    public ResponseEntity<?> getAllMember() {
        final List<MemberBasicResponse> memberBasicResponses = memberService.getMembers().stream()
            .map(MemberBasicResponse::new)
            .collect(Collectors.toList());
        return ResponseEntity.ok(memberBasicResponses);
    }

    @GetMapping("/time")
    public ResponseEntity<?> getTime(@ModelAttribute CreateMemberRequest request) {
//        log.info("requestTime={}", request.getLocalDateTime());
//        log.info("requestTime.ZoneOffset={}", request.getCreatedDate().getOffset());
        return ResponseEntity.ok(request);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getMember(@PathVariable("id") Long id) {
        final Member member = memberService.getMember(id);
        return ResponseEntity.ok(member);
    }

    @GetMapping("/osiv-off")
    public ResponseEntity<?> getAllMember1() {
        final List<Member> members = memberService.getMembers();
        return ResponseEntity.ok(members);
    }
}
