package me.kenux.playground.jpa.repository.dto;

import lombok.Data;
import me.kenux.playground.jpa.domain.OrderStatus;

@Data
public class OrderSearchCond {

    private String memberName;
    private OrderStatus orderStatus;
}
