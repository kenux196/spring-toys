package hello.aop.internalcall;

import hello.aop.internalcall.aop.CallAspect;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@Import(CallAspect.class)
@SpringBootTest(properties = "spring.main.allow-circular-references=true") // spring 2.6 이상에서는 순환참조 금지로 인해서 에러가 발생하므로 순환참조를 풀어준다.
class CallServiceV1Test {

    @Autowired
    CallServiceV1 callServiceV1;

    @Test
    void external() {
        callServiceV1.external();
    }
}