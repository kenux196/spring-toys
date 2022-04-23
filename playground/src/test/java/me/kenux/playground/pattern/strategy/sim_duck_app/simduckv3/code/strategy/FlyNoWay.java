package me.kenux.playground.pattern.strategy.sim_duck_app.simduckv3.code.strategy;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FlyNoWay implements FlyBehavior {
    @Override
    public void fly() {
        log.info("날지 못한다.");
    }
}
