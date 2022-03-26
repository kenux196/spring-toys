package me.kenux.jpabook.jpashop.service;

import lombok.RequiredArgsConstructor;
import me.kenux.jpabook.jpashop.domain.Item;
import me.kenux.jpabook.jpashop.repository.ItemRepository;
import me.kenux.jpabook.jpashop.web.BookForm;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    @Transactional
    public void saveItem(Item item) {
        itemRepository.save(item);
    }

    public List<Item> findItems() {
        return itemRepository.findAll();
    }

    public Item findOne(Long itemId) {
        return itemRepository.findOne(itemId);
    }

    @Transactional
    public void updateItem(BookForm bookForm) {
        // item을 조회해서 영속 상태로 만든다.
        Item item = itemRepository.findOne(bookForm.getId());

        // 값을 변경한다.
        item.setName(bookForm.getName());
        item.setPrice(bookForm.getPrice());
        item.setStockQuantity(bookForm.getStockQuantity());

        // 따로 save or merge를 하지 않아도 dirty check가 되어서 트랜잭션 끝날때 Update query가 나간다.
    }
}
