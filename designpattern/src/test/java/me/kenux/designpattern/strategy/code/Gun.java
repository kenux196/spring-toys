package me.kenux.designpattern.strategy.code;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Gun implements Weapon {
    @Override
    public void attack() {
        log.info("총 쏘기");
    }
}