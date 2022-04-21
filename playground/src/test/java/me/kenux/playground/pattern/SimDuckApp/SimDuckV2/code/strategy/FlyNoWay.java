package me.kenux.playground.pattern.SimDuckApp.SimDuckV2.code.strategy;

import lombok.extern.slf4j.Slf4j;
import me.kenux.playground.pattern.SimDuckApp.SimDuckV2.code.strategy.FlyBehavior;

@Slf4j
public class FlyNoWay implements FlyBehavior {
    @Override
    public void fly() {
        log.info("날지 못한다.");
    }
}
