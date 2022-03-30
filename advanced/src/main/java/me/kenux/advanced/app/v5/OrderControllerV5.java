package me.kenux.advanced.app.v5;

import lombok.RequiredArgsConstructor;
import me.kenux.advanced.trace.logtrace.LogTrace;
import me.kenux.advanced.trace.strategy.Strategy;
import me.kenux.advanced.trace.strategy.StrategyContext;
import me.kenux.advanced.trace.template.AbstractTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OrderControllerV5 {
    private final OrderServiceV5 orderService;
    private final LogTrace trace;

    @GetMapping("/v5/request")
    public String request(String itemId) {
        StrategyContext<String> strategyContext = new StrategyContext<>(trace);
        return strategyContext.execute("OrderController.request()", () -> {
            orderService.orderItem(itemId);
            return "ok";
        });
    }
}
