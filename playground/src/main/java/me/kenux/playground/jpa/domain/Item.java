package me.kenux.playground.jpa.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "item")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@ToString
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Integer price;

    private Integer quantity;

    public Item(Long id, String name, Integer price, Integer quantity) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public Item(String name, Integer price, Integer quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }
}
