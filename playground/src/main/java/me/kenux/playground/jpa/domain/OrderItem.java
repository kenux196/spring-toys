package me.kenux.playground.jpa.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "order_item")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_item_id")
    private Long id;

    @Column(name = "item_id")
    private Long itemId;

    @Column(name = "order_id")
    private Long orderId;

    private int price;  // 주문 가격

    private int count;  // 주문 수량

    public OrderItem(Long itemId, Long orderId, int price, int count) {
        this.itemId = itemId;
        this.orderId = orderId;
        this.price = price;
        this.count = count;
    }
}
