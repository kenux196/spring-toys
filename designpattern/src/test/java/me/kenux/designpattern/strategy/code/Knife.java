package me.kenux.designpattern.strategy.code;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Knife implements Weapon {
    @Override
    public void attack() {
        log.info("칼 공격");
    }
}