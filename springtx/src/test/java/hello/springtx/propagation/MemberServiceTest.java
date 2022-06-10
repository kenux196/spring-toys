package hello.springtx.propagation;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.UnexpectedRollbackException;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
class MemberServiceTest {

    @Autowired
    MemberService memberService;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    MemberRepository2 memberRepository2;

    @Autowired
    LogRepository logRepository;

    /**
     * 서비스 계층에 트랜잭션이 없을 때 - 커밋
     * MemberService    @Transactional: OFF
     * MemberRepository @Transactional: ON
     * LogRepository    @Transactional: ON
     */
    @Test
    void outerTxOff_success() {
        // given
        String username = "outerTxOff_success";

        // when
        memberService.joinV1(username);

        // then: 모든 데이터가 정상 저장된다.
        assertTrue(memberRepository.find(username).isPresent());
        assertTrue(logRepository.find(username).isPresent());
    }

    /**
     * 서비스 계층에 트랜잭션이 없을 때 - 롤백
     * MemberService    @Transactional: OFF
     * MemberRepository @Transactional: ON
     * LogRepository    @Transactional: ON Exception
     */
    @Test
    void outerTxOff_fail() {
        // given
        String username = "로그예외_outerTxOff_fail";

        // when
        assertThatThrownBy(() -> memberService.joinV1(username))
                .isInstanceOf(RuntimeException.class);

        // then: 완전히 롤백되지 않고, member 데이터가 남아서 저장된다.
        assertTrue(memberRepository.find(username).isPresent());
        assertTrue(logRepository.find(username).isEmpty());
    }

    /**
     * 단일 트랜잭션
     * MemberService    @Transactional: ON
     * MemberRepository @Transactional: OFF
     * LogRepository    @Transactional: OFF
     */
    @Test
    void singleTx() {
        // given
        String username = "singleTx";

        // when
        memberService.joinV1(username);

        // then: 모든 데이터가 정상 저장된다.
        assertTrue(memberRepository.find(username).isPresent());
        assertTrue(logRepository.find(username).isPresent());
    }

    /**
     * 모든 트랜잭션
     * MemberService    @Transactional: ON
     * MemberRepository @Transactional: ON
     * LogRepository    @Transactional: ON
     */
    @Test
    void outerTxOn_success() {
        // given
        String username = "outerTxOn_success";

        // when
        memberService.joinV1(username);

        // then: 모든 데이터가 정상 저장된다.
        assertTrue(memberRepository.find(username).isPresent());
        assertTrue(logRepository.find(username).isPresent());
    }

    /**
     * 모든 트랜잭션 : LogRepository 예외 발생
     * MemberService    @Transactional: ON
     * MemberRepository @Transactional: ON
     * LogRepository    @Transactional: ON Exception
     */
    @Test
    void outerTxOn_fail() {
        // given
        String username = "로그예외_outerTxOn_fail";

        // when
        assertThatThrownBy(() -> memberService.joinV1(username))
                .isInstanceOf(RuntimeException.class);

        // then: 모든 데이터가 롤백된다.
        assertTrue(memberRepository.find(username).isEmpty());
        assertTrue(logRepository.find(username).isEmpty());
    }

    /**
     * 모든 트랜잭션 : LogRepository 예외 발생 -> Service 정상 흐름으로 변환 -> 실패하는 방법
     * MemberService    @Transactional: ON
     * MemberRepository @Transactional: ON
     * LogRepository    @Transactional: ON Exception
     */
    @Test
    void recoverException_fail() {
        // given
        String username = "로그예외_recoverException_fail";

        // when
        assertThatThrownBy(() -> memberService.joinV2(username))
                .isInstanceOf(UnexpectedRollbackException.class);

        // then: 모든 데이터가 롤백된다.
        assertTrue(memberRepository.find(username).isEmpty());
        assertTrue(logRepository.find(username).isEmpty());
    }

    /**
     * 물리 트랜잭션 분리
     * MemberService    @Transactional: ON
     * MemberRepository @Transactional: ON
     * LogRepository    @Transactional(REQUIRES_NEW): Exception
     */
    @Test
    void recoverException_success() {
        // given
        String username = "로그예외_recoverException_success";

        // when
        memberService.joinV2(username);

        // then: 모든 데이터가 롤백된다.
        assertTrue(memberRepository.find(username).isPresent());
        assertTrue(logRepository.find(username).isEmpty());
    }

    @Test
    void findMemberTest() {
        // given
        String username = "newMember";
        memberService.joinV2(username);

        // when
        Optional<Member> member = memberService.getMember(username);

        // then
        assertTrue(member.isPresent());

    }

    @Test
    void findMember2Test() {
        // given
        String username = "newMember";
        Member member = memberService.joinV2(username);

        // when
        Optional<Member> findMember = memberService.getMember2(member.getId());
        Optional<Member> findMember2 = memberService.getMember2(member.getId());

        // then
        assertTrue(findMember.isPresent());

    }
}