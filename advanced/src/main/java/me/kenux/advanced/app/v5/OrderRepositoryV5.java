package me.kenux.advanced.app.v5;

import lombok.RequiredArgsConstructor;
import me.kenux.advanced.trace.logtrace.LogTrace;
import me.kenux.advanced.trace.strategy.Strategy;
import me.kenux.advanced.trace.strategy.StrategyContext;
import me.kenux.advanced.trace.template.AbstractTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryV5 {

    private final LogTrace trace;

    public void save(String itemId) {
        StrategyContext<Void> context = new StrategyContext<>(trace);
        context.execute("OrderRepository.save()", () -> {
            // 저장 로직
            if (itemId.equals("ex")) {
                throw new IllegalStateException("예외 발생");
            }
            sleep(1000);
            return null;
        });
    }

    private void sleep(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
