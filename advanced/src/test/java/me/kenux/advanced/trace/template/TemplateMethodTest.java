package me.kenux.advanced.trace.template;

import lombok.extern.slf4j.Slf4j;
import me.kenux.advanced.trace.template.code.AbstractTemplate;
import me.kenux.advanced.trace.template.code.SubClassLogic1;
import me.kenux.advanced.trace.template.code.SubClassLogic2;
import org.junit.jupiter.api.Test;

@Slf4j
class TemplateMethodTest {

    @Test
    void templateMethodV0() throws InterruptedException {
        logic1();
        logic2();
    }

    private void logic1() throws InterruptedException {
        long startTime = System.currentTimeMillis();
        // 비즈니스 로직 실행
        log.info("비즈니스 로직 1 실행");
        Thread.sleep(100);
        // 비즈니스 로직 종료
        long endTime = System.currentTimeMillis();
        long resultTime = endTime - startTime;
        log.info("resultTime={}", resultTime);
    }

    private void logic2() throws InterruptedException {
        long startTime = System.currentTimeMillis();
        // 비즈니스 로직 실행
        log.info("비즈니스 로직 2 실행");
        Thread.sleep(200);
        // 비즈니스 로직 종료
        long endTime = System.currentTimeMillis();
        long resultTime = endTime - startTime;
        log.info("resultTime={}", resultTime);
    }

    @Test
    void templateMethodV1() {
        AbstractTemplate template1 = new SubClassLogic1();
        template1.execute();

        AbstractTemplate template2 = new SubClassLogic2();
        template2.execute();
    }
}
