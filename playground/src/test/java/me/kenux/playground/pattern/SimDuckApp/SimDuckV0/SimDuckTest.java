package me.kenux.playground.pattern.SimDuckApp.SimDuckV0;

import lombok.extern.slf4j.Slf4j;
import me.kenux.playground.pattern.SimDuckApp.SimDuckV0.code.*;
import org.junit.jupiter.api.Test;

@Slf4j
class SimDuckTest {

    @Test
    void demo1() {
        Duck mallardDuck = new MallardDuck("Mallard Duck");
        mallardDuck.display();
        mallardDuck.quack();
        mallardDuck.swim();
        mallardDuck.fly();

        Duck redHeadDuck = new RedHeadDuck("RedHead Duck");
        redHeadDuck.display();
        redHeadDuck.quack();
        redHeadDuck.swim();
        redHeadDuck.fly();

        Duck rubberDuck = new RubberDuck("고무 오리");
        rubberDuck.display();
        rubberDuck.quack();
        rubberDuck.swim();
        rubberDuck.fly(); // 고무 오리가 날고 있다. 문제가 있다.

        Duck decoyDuck = new DecoyDuck("나무 오리");
        decoyDuck.display();
        decoyDuck.quack();
        decoyDuck.swim();
        decoyDuck.fly(); // 고무 오리가 날고 있다. 문제가 있다.
    }

}
