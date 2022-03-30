package me.kenux.advanced.trace.strategy;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.kenux.advanced.trace.TraceStatus;
import me.kenux.advanced.trace.logtrace.LogTrace;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
public class StrategyContext<T> {

    private final LogTrace trace;

    public T execute(String message, Strategy<T> strategy) {
        TraceStatus status = null;
        try {
            status = trace.begin(message);

            // 로직 호출
            T result = strategy.call();

            trace.end(status);
            return result;
        } catch (Exception e) {
            trace.exception(status, e);
            throw e;
        }
    }
}
