package me.kenux.jpabook.jpashop.service;

import me.kenux.jpabook.jpashop.domain.Item;
import me.kenux.jpabook.jpashop.repository.ItemRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class ItemServiceTest {

    @Autowired
    private ItemRepository itemRepository;

    @Test
    void 아이템_추가() {
        // given
        Item item = new Item();
        item.setName("item1");

        // when
        itemRepository.save(item);

        // then
        Item findItem = itemRepository.findOne(item.getId());
        assertThat(findItem).isEqualTo(item);
    }

    @Test
    void 아이템_전체_조회() {
        // given
        for (int i = 0; i < 10; i++) {
            Item item = new Item();
            item.setName("item-" + i);
            itemRepository.save(item);
        }

        // when
        List<Item> items = itemRepository.findAll();

        // then
        assertThat(items).hasSize(10);
    }
}