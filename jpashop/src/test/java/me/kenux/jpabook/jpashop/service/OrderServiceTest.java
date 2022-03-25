package me.kenux.jpabook.jpashop.service;

import me.kenux.jpabook.jpashop.domain.*;
import me.kenux.jpabook.jpashop.exception.NotEnoughStockException;
import me.kenux.jpabook.jpashop.repository.OrderRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class OrderServiceTest {

    @PersistenceContext
    EntityManager em;

    @Autowired
    OrderService orderService;
    @Autowired
    OrderRepository orderRepository;

    @Test
    void 상품주문() {
        // given
        Member member = createMember();
        Item item = createBook("스프링 & JPA", 10000, 100);
        int orderCount = 10;

        // when
        Long orderId = orderService.order(member.getId(), item.getId(), orderCount);

        // then
        Order findOrder = orderRepository.findOne(orderId);
        assertThat(findOrder.getStatus()).isEqualTo(OrderStatus.ORDER);
        assertThat(findOrder.getOrderItems()).hasSize(1);
        assertThat(findOrder.getTotalPrice()).isEqualTo(10000 * orderCount);
        assertThat(item.getStockQuantity()).isEqualTo(100 - orderCount);
    }

    @Test
    void 재고수량을초과하는주문은_예외가발생한다() {
        // given
        Member member = createMember();
        Item item = createBook("스프링 & JPA", 10000, 10);
        int orderCount = 11;

        // when // then
        assertThatThrownBy(() -> orderService.order(member.getId(), item.getId(), orderCount))
                .isInstanceOf(NotEnoughStockException.class);
    }

    @Test
    void 주문취소() {
        // given
        Member member = createMember();
        Item item = createBook("스프링 & JPA", 10000, 10);
        int orderCount = 2;

        Long orderId = orderService.order(member.getId(), item.getId(), orderCount);

        // when
        orderService.cancelOrder(orderId);

        // then
        Order order = orderRepository.findOne(orderId);
        assertThat(order.getStatus()).isEqualTo(OrderStatus.CANCEL);
        assertThat(item.getStockQuantity()).isEqualTo(10);
    }

    private Book createBook(String name, int price, int count) {
        Book book = new Book();
        book.setName(name);
        book.setPrice(price);
        book.setStockQuantity(count);
        em.persist(book);
        return book;
    }

    private Member createMember() {
        Member member = new Member();
        member.setName("member1");
        member.setAddress(new Address("대구", "달성군", "12345"));
        em.persist(member);
        return member;
    }
}