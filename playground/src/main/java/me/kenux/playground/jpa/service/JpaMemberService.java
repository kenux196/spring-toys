package me.kenux.playground.jpa.service;

import lombok.RequiredArgsConstructor;
import me.kenux.playground.jpa.domain.Member;
import me.kenux.playground.jpa.repository.JpaMemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class JpaMemberService {

    private final JpaMemberRepository memberRepository;

    public Long join(Member member) {
        memberRepository.save(member);
        return member.getId();
    }
}
