package me.kenux.springsecurity.domain.member;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Collections;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService implements UserDetailsService {

    private final MemberRepository memberRepository;

    private final PasswordEncoder passwordEncoder;

    @PostConstruct
    public void init() {
        final Member testMember = new Member("kenux", "1234");
        testMember.setAuthorities(Collections.singleton(new Authority("ROLE_USER")));
        save(testMember);
    }

    public Member save(Member member) {
        if (member.getId() == null) {
            member.setPassword(passwordEncoder.encode(member.getPassword()));
        }
        return memberRepository.save(member);
    }

    public Member findMember(Long id) {
        return memberRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("사용자 정보를 찾을 수 없습니다."));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final Optional<Member> member = memberRepository.findByUsername(username);
        return member.orElseThrow(() -> new UsernameNotFoundException("사용자 정보를 찾을 수 없습니다."));
    }

    public void addAuthority(Long memberId, String authority) {
        final Member member = findMember(memberId);
        member.getAuthorities().add(new Authority(authority));
        memberRepository.save(member);
    }

    public void removeAuthority(Long memberId, String authority) {
        final Member member = findMember(memberId);
        member.getAuthorities().remove(new Authority(authority));
        memberRepository.save(member);
    }
}