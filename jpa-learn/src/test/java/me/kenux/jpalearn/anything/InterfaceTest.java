package me.kenux.jpalearn.anything;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class InterfaceTest {

    @Test
    void test1() {
        final C c = new CImpl();

        log.info("getC={}", c.getC());
        log.info("getB={}", c.getB());
        log.info("getA={}", c.getA());
    }

    public interface A {
        String getA();
    }

    public interface B extends A {
        default String getB() {
            return "Default B";
        };

        @Override
        default String getA() {
            return "BtoA";
        }
    }

    public interface C extends B {
        String getC();
    }

    public class CImpl implements C {
        @Override
        public String getC() {
            return "C";
        }
    }

}