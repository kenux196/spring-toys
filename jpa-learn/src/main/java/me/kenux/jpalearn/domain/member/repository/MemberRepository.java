package me.kenux.jpalearn.domain.member.repository;

import me.kenux.jpalearn.domain.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
