package me.kenux.playground.pattern.SimDuckApp.SimDuckV2.code.strategy;

import lombok.extern.slf4j.Slf4j;
import me.kenux.playground.pattern.SimDuckApp.SimDuckV2.code.strategy.QuackBehavior;

@Slf4j
public class Quack implements QuackBehavior {
    @Override
    public void quack() {
        log.info("꽥꽥");
    }
}
