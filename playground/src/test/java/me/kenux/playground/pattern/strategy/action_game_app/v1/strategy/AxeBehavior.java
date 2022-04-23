package me.kenux.playground.pattern.strategy.action_game_app.v1.strategy;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AxeBehavior implements WeaponBehavior {
    @Override
    public void useWeapon() {
        log.info("도끼를 휘드른다");
    }
}
