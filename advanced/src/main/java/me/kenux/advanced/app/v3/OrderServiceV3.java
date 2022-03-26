package me.kenux.advanced.app.v3;

import lombok.RequiredArgsConstructor;
import me.kenux.advanced.trace.TraceId;
import me.kenux.advanced.trace.TraceStatus;
import me.kenux.advanced.trace.hellotrace.HelloTraceV2;
import me.kenux.advanced.trace.logtrace.LogTrace;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceV3 {
    private final OrderRepositoryV3 orderRepository;
    private final LogTrace trace;

    public void orderItem(String itemId) {
        TraceStatus status = null;
        try {
            status = trace.begin("OrderService.orderItem()");
            orderRepository.save(itemId);
            trace.end(status);
        } catch (Exception e) {
            trace.exception(status, e);
            throw e;
        }
    }
}
