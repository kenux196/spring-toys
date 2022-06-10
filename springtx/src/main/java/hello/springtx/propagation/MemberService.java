package hello.springtx.propagation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final MemberRepository2 memberRepository2;
    private final LogRepository logRepository;

    public Optional<Member> getMember(String username) {
        log.info("== memberRepository.find() 호출 시작 ==");
        Optional<Member> findMember = memberRepository.find(username);
        log.info("== memberRepository.find() 호출 종료 ==");
        return findMember;
    }

    public Optional<Member> getMember2(Long id) {
        log.info("== memberRepository.find() 호출 시작 ==");
        Optional<Member> findMember = memberRepository2.findById(id);
        log.info("== memberRepository.find() 호출 종료 ==");
        return findMember;
    }

    @Transactional
    public void joinV1(String username) {
        Member member = new Member(username);
        Log logMessage = new Log(username);

        log.info("== memberRepository 호출 시작 ==");
        memberRepository.save(member);
        log.info("== memberRepository 호출 종료 ==");

        log.info("== logRepository 호출 시작 ==");
        logRepository.save(logMessage);
        log.info("== logRepository 호출 종료 ==");
    }

    @Transactional
    public Member joinV2(String username) {
        Member member = new Member(username);
        Log logMessage = new Log(username);

        log.info("== memberRepository 호출 시작 ==");
        memberRepository.save(member);
        log.info("== memberRepository 호출 종료 ==");

        log.info("== logRepository 호출 시작 ==");
        try {
            logRepository.save(logMessage);
        } catch (RuntimeException e) {
            log.info("log 저장에 실패했습니다. logMessage={}", logMessage.getMessage());
            log.info("정상 흐름 변환");
        }
        log.info("== logRepository 호출 종료 ==");

        return member;
    }
}
