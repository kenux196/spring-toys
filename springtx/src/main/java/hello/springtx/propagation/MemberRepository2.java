package hello.springtx.propagation;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository2 extends JpaRepository<Member, Long> {
}
