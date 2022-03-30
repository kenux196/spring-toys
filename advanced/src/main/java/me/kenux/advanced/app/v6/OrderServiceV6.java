package me.kenux.advanced.app.v6;

import lombok.RequiredArgsConstructor;
import me.kenux.advanced.trace.callback.TraceTemplate;
import me.kenux.advanced.trace.logtrace.LogTrace;
import me.kenux.advanced.trace.strategy.StrategyContext;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceV6 {
    private final OrderRepositoryV6 orderRepository;
    private final TraceTemplate template;

    public void orderItem(String itemId) {
        template.execute("OrderService.orderItem()", () -> {
            orderRepository.save(itemId);
            return null;
        });
    }
}
