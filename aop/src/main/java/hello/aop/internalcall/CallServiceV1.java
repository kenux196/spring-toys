package hello.aop.internalcall;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * 참고: 생성자 주입은 순환(cycle) 참조를 만들기 때문에 실패한다.
 * spring boot 2.6 이상에서 순환참조 금지가 걸려있는 경우에는 사용하기 어려운 방법이다.
 */
@Slf4j
@Component
public class CallServiceV1 {

    private CallServiceV1 callServiceV1;

    @Autowired
    public void setCallServiceV1(CallServiceV1 callServiceV1) {
        log.info("target={}", callServiceV1.getClass());
        this.callServiceV1 = callServiceV1;
    }

    public void external() {
        log.info("call external");
        callServiceV1.internal(); // 외부 메서드 호출
    }

    public void internal() {
        log.info("call internal");
    }
}
