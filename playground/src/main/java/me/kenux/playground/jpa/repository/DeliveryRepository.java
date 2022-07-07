package me.kenux.playground.jpa.repository;

import me.kenux.playground.jpa.domain.Delivery;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeliveryRepository extends JpaRepository<Delivery, Long> {
}
