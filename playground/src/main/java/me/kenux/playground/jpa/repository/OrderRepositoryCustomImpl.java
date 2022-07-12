package me.kenux.playground.jpa.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import me.kenux.playground.jpa.domain.Order;
import me.kenux.playground.jpa.repository.dto.OrderSearchCond;

import java.util.List;
import java.util.stream.Collectors;

import static me.kenux.playground.jpa.domain.QOrder.order;

@RequiredArgsConstructor
public class OrderRepositoryCustomImpl implements OrderRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<Order> findAllBySearchCond(OrderSearchCond orderSearchCond) {
        return queryFactory.select(order)
            .from(order)
            .where(eqMemberName(orderSearchCond), eqOrderStatus(orderSearchCond))
            .fetchAll().stream()
            .collect(Collectors.toList());
    }

    private BooleanExpression eqMemberName(OrderSearchCond cond) {
        if (cond.getMemberName() != null) {
            return order.member.name.eq(cond.getMemberName());
        }
        return null;
    }

    private BooleanExpression eqOrderStatus(OrderSearchCond cond) {
        if (cond.getOrderStatus() != null) {
            return order.orderStatus.eq(cond.getOrderStatus());
        }
        return null;
    }
}
