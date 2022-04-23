package me.kenux.playground.pattern.strategy.sim_duck_app.simduckv3.code.strategy;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MuteQuack implements QuackBehavior {
    @Override
    public void quack() {
        log.info("소리내지 못한다.");
    }
}
