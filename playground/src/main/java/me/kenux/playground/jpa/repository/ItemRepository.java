package me.kenux.playground.jpa.repository;

import me.kenux.playground.jpa.domain.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
}
