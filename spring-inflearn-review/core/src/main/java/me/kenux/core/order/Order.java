package me.kenux.core.order;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class Order {

    private final Long memberId;
    private final String itemName;
    private final int itemPrice;
    private final int discountPrice;

    public Order(Long memberId, String itemName, int itemPrice, int discountPrice) {
        this.memberId = memberId;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.discountPrice = discountPrice;
    }

    public int calculatePrice() {
        return itemPrice - discountPrice;
    }
}
