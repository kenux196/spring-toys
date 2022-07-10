package me.kenux.playground.jpa.repository;

import me.kenux.playground.jpa.domain.Order;
import me.kenux.playground.jpa.repository.dto.OrderSearchCond;

import java.util.List;

public interface OrderRepositoryCustom {

    List<Order> findAllBySearchCond(OrderSearchCond orderSearchCond);
}
