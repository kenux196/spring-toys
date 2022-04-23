package me.kenux.playground.pattern.strategy.action_game_app.v1.strategy;

import lombok.extern.slf4j.Slf4j;
import me.kenux.playground.pattern.strategy.action_game_app.v1.strategy.WeaponBehavior;

@Slf4j
public class KnifeBehavior implements WeaponBehavior {
    @Override
    public void useWeapon() {
        log.info("칼을 휘두른다");
    }
}
