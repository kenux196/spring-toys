package me.kenux.playground.jpa.event;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.kenux.playground.jpa.domain.Member;
import me.kenux.playground.jpa.repository.MemberRepository;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


@Slf4j
@Component
@RequiredArgsConstructor
public class AppStartListener implements ApplicationListener<ApplicationStartedEvent> {

    private final MemberRepository memberRepository;

    private final Random random = new Random();

    @Override
    public void onApplicationEvent(ApplicationStartedEvent event) {
        initMemberData();
    }

    private void initMemberData() {
        List<Member> memberList = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            final Member member = new Member("회원" + i);
            member.changeAge(generateRandomAge());
            memberList.add(member);
        }
        memberRepository.saveAll(memberList);
    }

    private int generateRandomAge() {
        return random.nextInt(100);
    }
}
