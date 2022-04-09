package hello.proxy.postprocessor;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * BeanPostProcessor 적용 테스트
 */
@Slf4j
class BeanPostProcessTest {

    @Test
    void postProcessor() {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(BeanPostProcessorConfig.class);

        // beanA 이름으로 B 객체가 빈으로 등록된다.
        B b = applicationContext.getBean("beanA", B.class);
        b.helloB();

        // A는 빈으로 등록되지 않는다.
        assertThrows(NoSuchBeanDefinitionException.class, () -> applicationContext.getBean(A.class));
    }

    @Configuration
    static class BeanPostProcessorConfig {

        @Bean(name = "beanA")
        public A a() {
            log.info("beanA 등록");
            return new A();
        }

        @Bean
        public AToBPostProcessor helloPostProcessor() {
            log.info("helloPostProcessor 빈 등록");
            return new AToBPostProcessor();
        }

        @Bean(name = "beanC")
        public C c() {
            log.info("beanC 등록");
            return new C();
        }
    }

    static class A {
        public A() {
            log.info("A 객체 생성");
        }

        public void helloA() {
            log.info("hello A");
        }
    }

    static class B {
        public B() {
            log.info("B 객체 생성");
        }

        public void helloB() {
            log.info("hello B");
        }
    }

    static class C {
        public C() {
            log.info("C 객체 생성");
        }
    }


    static class AToBPostProcessor implements BeanPostProcessor {
        public AToBPostProcessor() {
            log.info("AToBPostProcessor 객체 생성");
        }

        @Override
        public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
            log.info("postProcessBeforeInitialization bean={} beanName={}", bean, beanName);
            return bean;
        }

        @Override
        public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
            log.info("postProcessAfterInitialization bean={} beanName-{}", beanName, bean);
            if (bean instanceof A) {
                return new B();
            }
            return bean;
        }
    }

}
