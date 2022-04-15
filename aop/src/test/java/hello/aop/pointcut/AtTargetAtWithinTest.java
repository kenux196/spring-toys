package hello.aop.pointcut;

import hello.aop.member.annotaion.ClassAop;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

@Slf4j
@Import(AtTargetAtWithinTest.Config.class)
@SpringBootTest
class AtTargetAtWithinTest {

    @Autowired
    Child child;

    @Test
    void success() {
        log.info("child Proxy={}", child.getClass());
        child.childMethod(); // 부모, 자식 모두 있는 메서드
        child.parentMethod(); // 부모 클래스만 있는 메서드
    }


    static class Config {
        @Bean
        public Parent parent() {
            return new Parent();
        }

        @Bean
        public Child child() {
            return new Child();
        }

        @Bean
        public AtTargetAtWithinAspect atTargetAtWithinAspect() {
            return new AtTargetAtWithinAspect();
        }
    }

    static class Parent {
        public void parentMethod() {} // 부모에만 있는 메서드
    }

    @ClassAop
    static class Child extends Parent {
        public void childMethod() {}
    }

    @Aspect
    static class AtTargetAtWithinAspect {

        // @Target: 인스턴스 기준으로 모든 메서드의 조인 포인트를 선정.
        // 부모 타입의 메서드에도 적용된다.
        @Around("execution(* hello.aop..*(..)) && @target(hello.aop.member.annotaion.ClassAop)")
        public Object atTarget(ProceedingJoinPoint joinPoint) throws Throwable {
            log.info("[@Target] {}", joinPoint.getSignature());
            return joinPoint.proceed();
        }

        @Around("execution(* hello.aop..*(..)) && @within(hello.aop.member.annotaion.ClassAop)")
        public Object atWithin(ProceedingJoinPoint joinPoint) throws Throwable {
            log.info("[@Within] {}", joinPoint.getSignature());
            return joinPoint.proceed();
        }
    }
}
