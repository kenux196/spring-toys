package me.kenux.playground.jpa.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import me.kenux.playground.jpa.domain.Member;
import me.kenux.playground.jpa.repository.dto.MemberSearchCond;

import java.util.List;

import static me.kenux.playground.jpa.domain.QMember.*;

@RequiredArgsConstructor
public class MemberRepositoryCustomImpl implements MemberRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<Member> findAllByCondition(MemberSearchCond cond) {
        return queryFactory.select(member)
                .from(member)
                .where(likeName(cond.getName()))
                .fetch();
    }

    private BooleanExpression likeName(String name) {
        if (name != null) {
            return member.name.like("%" + name + "%");
        }
        return null;
    }

//    private BooleanExpression lessOrEqualAge(Integer age) {
//        if (age != null) {
//            return member.age.loe(age);
//        }
//        return null;
//    }
}
