package me.kenux.playground.pattern.SimDuckApp.SimDuckV2;

import lombok.extern.slf4j.Slf4j;
import me.kenux.playground.pattern.SimDuckApp.SimDuckV2.code.*;
import me.kenux.playground.pattern.SimDuckApp.SimDuckV2.code.strategy.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@Slf4j
class SimDuckV2Test {

    @Test
    void demo1() {
        FlyBehavior flyWithWings = new FlyWithWings();
        FlyBehavior flyNoWay = new FlyNoWay();
        QuackBehavior quack = new Quack();
        QuackBehavior squeak = new Squeak();
        QuackBehavior muteQuack = new MuteQuack();

        Duck mallardDuck = new MallardDuck("Mallard Duck", flyWithWings, quack);
        mallardDuck.display();
        mallardDuck.performFly();
        mallardDuck.swim();
        mallardDuck.performQuack();

        RedHeadDuck redHeadDuck = new RedHeadDuck("RedHead Duck", flyWithWings, quack);
        redHeadDuck.display();
        redHeadDuck.performFly();
        redHeadDuck.swim();
        redHeadDuck.performQuack();

        RubberDuck rubberDuck = new RubberDuck("고무 오리", flyNoWay, squeak);
        rubberDuck.display();
        rubberDuck.performFly();
        rubberDuck.swim();
        rubberDuck.performQuack();


        DecoyDuck decoyDuck = new DecoyDuck("나무 오리", flyNoWay, muteQuack);
        decoyDuck.display();
        decoyDuck.performFly();
        decoyDuck.swim();
        decoyDuck.performQuack();
    }

    @Test
    @DisplayName("나무 오리에도 삑삑이 소리가 나도록 기능을 변경한다.")
    void test2() {
        FlyBehavior flyNoWay = new FlyNoWay();
        QuackBehavior squeak = new Squeak();
        QuackBehavior muteQuack = new MuteQuack();

        DecoyDuck decoyDuck = new DecoyDuck("나무 오리", flyNoWay, muteQuack);
        decoyDuck.performFly();
        log.info("---  기능 변경 전 ---");
        decoyDuck.performQuack();
        decoyDuck.changeQuackBehavior(squeak);
        log.info("---  기능 변경 후 ---");
        decoyDuck.performQuack();
    }

}
