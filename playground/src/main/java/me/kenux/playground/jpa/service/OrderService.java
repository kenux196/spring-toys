package me.kenux.playground.jpa.service;

import lombok.RequiredArgsConstructor;
import me.kenux.playground.jpa.domain.*;
import me.kenux.playground.jpa.repository.MemberRepository;
import me.kenux.playground.jpa.repository.OrderRepository;
import me.kenux.playground.jpa.repository.dto.OrderSearchCond;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;
    private final ItemService itemService;

    @Transactional
    public Long order(Long memberId, Long itemId, int count) {
        Member member = memberRepository.findById(memberId).orElseThrow();
        Item item = itemService.findOne(itemId);

        Delivery delivery = new Delivery(member.getAddress());
        OrderItem orderItem = OrderItem.createOrderItem(item, item.getPrice(), count);
        Order order = Order.createOrder(member, delivery, orderItem);

        orderRepository.save(order);
        return order.getId();
    }

    @Transactional
    public void cancelOrder(Long orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow();
        order.cancel();
    }

    public List<Order> findOrders(OrderSearchCond orderSearchCond) {
        return orderRepository.findAllBySearchCond(orderSearchCond);
    }
}
