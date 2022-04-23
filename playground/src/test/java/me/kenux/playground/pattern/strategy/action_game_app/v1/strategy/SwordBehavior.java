package me.kenux.playground.pattern.strategy.action_game_app.v1.strategy;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SwordBehavior implements WeaponBehavior {
    @Override
    public void useWeapon() {
        log.info("검을 휘두른다");
    }
}
