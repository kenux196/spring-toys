package me.kenux.playground.jpa.repository;

import lombok.RequiredArgsConstructor;
import me.kenux.playground.jpa.domain.Member;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class JpaMemberRepository {

    private final EntityManager em;

    @Transactional
    public void save(Member member) {
        em.persist(member);
    }

    public Optional<Member> findById(Long id) {
        Member member = em.find(Member.class, id);
        return Optional.ofNullable(member);
    }

    public List<Member> findAll() {
        String query = "select m from Member m";
        return em.createQuery(query, Member.class)
                .getResultList();
    }
}
