package me.kenux.designpattern.strategy.code;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Sword implements Weapon {
    @Override
    public void attack() {
        log.info("검 공격");
    }
}