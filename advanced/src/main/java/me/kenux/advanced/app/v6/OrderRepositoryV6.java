package me.kenux.advanced.app.v6;

import lombok.RequiredArgsConstructor;
import me.kenux.advanced.trace.callback.TraceTemplate;
import me.kenux.advanced.trace.logtrace.LogTrace;
import me.kenux.advanced.trace.strategy.StrategyContext;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryV6 {

    private final TraceTemplate template;

    public void save(String itemId) {
        template.execute("OrderRepository.save()", () -> {
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
