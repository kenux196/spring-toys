package me.kenux.advanced.app.v6;

import lombok.RequiredArgsConstructor;
import me.kenux.advanced.trace.callback.TraceTemplate;
import me.kenux.advanced.trace.logtrace.LogTrace;
import me.kenux.advanced.trace.strategy.StrategyContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OrderControllerV6 {
    private final OrderServiceV6 orderService;
    private final TraceTemplate template;

    @GetMapping("/v6/request")
    public String request(String itemId) {
        return template.execute("OrderController.request()", () -> {
            orderService.orderItem(itemId);
            return "ok";
        });
    }
}
