package me.kenux.playground.jpa.repository;

import me.kenux.playground.jpa.domain.Member;
import me.kenux.playground.jpa.repository.dto.MemberSearchCond;

import java.util.List;

public interface MemberRepositoryCustom {

    List<Member> findAllByCondition(MemberSearchCond cond);
}
