package me.kenux.playground.jpa.service;

import lombok.extern.slf4j.Slf4j;
import me.kenux.playground.jpa.domain.*;
import me.kenux.playground.jpa.repository.ItemRepository;
import me.kenux.playground.jpa.repository.MemberRepository;
import me.kenux.playground.jpa.repository.OrderRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@SpringBootTest
@Transactional
class OrderServiceTest {

    @Autowired
    OrderService orderService;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    EntityManager em;

    @Test
    void order() {
        // given
        final Member member = new Member("member1", new Address("대구", "대실력남로", "35"));
        memberRepository.save(member);

        final Item item = new Book("아이템", 1000, 10, "김작가", "123123");
        itemRepository.save(item);
        em.flush();
        em.clear();
        log.info("데이터 준비 완료");

        // when
        final Long orderId = orderService.order(1L, 1L, 1);

        // then
        final Optional<Order> findOrder = orderRepository.findById(orderId);
        assertThat(findOrder).isPresent();
        assertThat(findOrder.get().getOrderStatus()).isEqualTo(OrderStatus.ORDER);
    }

}
