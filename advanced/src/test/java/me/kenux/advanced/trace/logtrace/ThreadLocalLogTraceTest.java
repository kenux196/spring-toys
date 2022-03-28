package me.kenux.advanced.trace.logtrace;

import me.kenux.advanced.trace.TraceStatus;
import org.junit.jupiter.api.Test;

class ThreadLocalLogTraceTest {

    private LogTrace logTrace = new ThreadLocalLogTrace();

    @Test
    void begin_end_level2() {
        TraceStatus status1 = logTrace.begin("hello1");
        TraceStatus status2 = logTrace.begin("hello2");
        logTrace.end(status2);
        logTrace.end(status1);
    }

    @Test
    void begin_exception_level2() {
        TraceStatus status1 = logTrace.begin("hello1");
        TraceStatus status2 = logTrace.begin("hello2");
        logTrace.exception(status2, new IllegalStateException());
        logTrace.exception(status1, new IllegalStateException());
    }

}