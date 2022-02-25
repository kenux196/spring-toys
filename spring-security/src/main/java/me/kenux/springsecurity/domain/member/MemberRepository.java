package me.kenux.springsecurity.domain.member;

import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.*;

@Repository
public class MemberRepository {

    private static final Map<Long, Member> store = new HashMap<>();
    private static Long sequence = 0L;

    public Member save(Member member) {
        if (member.getId() == null) {
            member.setId(++sequence);
        }
        store.put(member.getId(), member);
        return member;
    }

    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    public Optional<Member> findByUsername(String username) {
        return store.values().stream()
                .filter(member -> member.getName().equals(username))
                .findFirst();
    }

    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore() {
        store.clear();
    }
}