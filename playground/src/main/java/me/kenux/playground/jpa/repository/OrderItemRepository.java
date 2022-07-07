package me.kenux.playground.jpa.repository;

import me.kenux.playground.jpa.domain.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
