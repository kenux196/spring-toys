package hello.proxy.postprocessor;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * 빈 후처리기(BeanPostProcessor) 테스트
 * Bean 객체를 스프링 빈 저장소에 등록하기 전에 Bean 객체를 조작하거나 다른 객체로 바꿔치기 한다.
 * 후처리 적용하지 않은 예제
 */

@Slf4j
class BasicTest {

    @Test
    @DisplayName("일반적인 Bean 등록 과정")
    void basicConfig() {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(BasicConfig.class);
        A a = applicationContext.getBean("beanA", A.class);
        a.helloA();

        // B는 빈으로 등록하지 않는다.
        assertThatThrownBy(() -> applicationContext.getBean(B.class))
                .isInstanceOf(NoSuchBeanDefinitionException.class);
    }

    @Configuration
    static class BasicConfig {
        @Bean(name = "beanA")
        public A a() {
            return new A();
        }
    }

    static class A {
        public void helloA() {
            log.info("helloA");
        }
    }

    static class B {
        public void helloB() {
            log.info("helloB");
        }
    }

}
