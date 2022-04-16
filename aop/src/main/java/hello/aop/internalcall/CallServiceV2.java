package hello.aop.internalcall;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;


/**
 * ObjectProvider(Provider), ApplicationContext 를 사용해서 지연(lazy) 조회
 * spring boot 2.6 이상에서 순환참조 금지가 걸려있는 경우에는 사용하기 어려운 방법이다.
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class CallServiceV2 {

//    private final ApplicationContext applicationContext;
    private final ObjectProvider<CallServiceV2> callServiceV2Provider;

    public void external() {
        log.info("call external");
//        CallServiceV2 callServiceV2 = applicationContext.getBean(CallServiceV2.class);
        CallServiceV2 callServiceV2 = callServiceV2Provider.getObject();
        callServiceV2.internal(); // 외부 메서드 호출
    }

    public void internal() {
        log.info("call internal");
    }
}
