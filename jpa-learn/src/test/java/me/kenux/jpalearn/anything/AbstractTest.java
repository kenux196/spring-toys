package me.kenux.jpalearn.anything;

import org.junit.jupiter.api.Test;

class AbstractTest {


    @Test
    void test1() {
        final Car car1 = new BUS();
        System.out.println("car1.getName() = " + car1.getName());
    }

    public abstract class Car {
        abstract String getName();
    }

    class BUS extends Car {

        @Override
        String getName() {
            return "BUS";
        }
    }
}