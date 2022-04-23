package me.kenux.playground.pattern.strategy.action_game_app.v1;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Knight extends Character {

    @Override
    public void fight() {
        weaponBehavior.useWeapon();
    }
}
