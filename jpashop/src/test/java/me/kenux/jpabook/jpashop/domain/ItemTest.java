package me.kenux.jpabook.jpashop.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ItemTest {

    @Test
    void addStockTest() {
        // given
        Item item = getItem();

        // when
        item.addStock(100);

        // then
        assertThat(item.getStockQuantity()).isEqualTo(200);
    }

    @Test
    void removeStockTest() {
        // given
        Item item = getItem();

        // when
        item.removeStock(50);

        // then
        assertThat(item.getStockQuantity()).isEqualTo(50);
    }

    private Item getItem() {
        Item item = new Item();
        item.setName("itemA");
        item.setStockQuantity(100);
        return item;
    }

}