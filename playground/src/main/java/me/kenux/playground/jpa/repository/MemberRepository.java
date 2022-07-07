package me.kenux.playground.jpa.repository;

import me.kenux.playground.jpa.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long> {
    @Query("select m from Member m where m.address.city = :city")
    List<Member> findAllByCity(@Param("city") String city);

    List<Member> findAllByName(String name);
}
