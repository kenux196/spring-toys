package me.kenux.playground.jpa.repository;

import lombok.extern.slf4j.Slf4j;
import me.kenux.playground.config.QuerydslConfig;
import me.kenux.playground.jpa.domain.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@DataJpaTest
@Import(QuerydslConfig.class)
@Transactional
class OrderRepositoryTest {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    DeliveryRepository deliveryRepository;

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    OrderItemRepository orderItemRepository;


    @Test
    void save() {
        // given
        final Member member = new Member("member1");
        memberRepository.save(member);

        final Delivery delivery = new Delivery(new Address("대구", "달구벌대로", "323"));
        deliveryRepository.save(delivery);

        final Item item = new Book("아이템", 1000, 10, "김작가", "123123");
        itemRepository.save(item);

        OrderItem orderItem = OrderItem.createOrderItem(item, 1000, 1);
        orderItemRepository.save(orderItem);

        final Order order = Order.createOrder(member, delivery, orderItem);
        orderRepository.save(order);
    }


}
