package me.kenux.playground.pattern.SimDuckApp.SimDuckV1;

import lombok.extern.slf4j.Slf4j;
import me.kenux.playground.pattern.SimDuckApp.SimDuckV1.code.DecoyDuck;
import me.kenux.playground.pattern.SimDuckApp.SimDuckV1.code.MallardDuck;
import me.kenux.playground.pattern.SimDuckApp.SimDuckV1.code.RedHeadDuck;
import me.kenux.playground.pattern.SimDuckApp.SimDuckV1.code.RubberDuck;
import org.junit.jupiter.api.Test;

@Slf4j
class SimDuckTest {

    @Test
    void demo1() {
        MallardDuck mallardDuck = new MallardDuck("Mallard Duck");
        mallardDuck.display();
        mallardDuck.quack();
        mallardDuck.swim();
        mallardDuck.fly();

        RedHeadDuck redHeadDuck = new RedHeadDuck("RedHead Duck");
        redHeadDuck.display();
        redHeadDuck.quack();
        redHeadDuck.swim();
        redHeadDuck.fly();

        RubberDuck rubberDuck = new RubberDuck("고무 오리");
        rubberDuck.display();
        rubberDuck.quack();
        rubberDuck.swim();

        DecoyDuck decoyDuck = new DecoyDuck("나무 오리");
        decoyDuck.display();
        decoyDuck.swim();
    }

}
