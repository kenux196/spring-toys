package me.kenux.playground.pattern.strategy.action_game_app;

import lombok.extern.slf4j.Slf4j;
import me.kenux.playground.pattern.strategy.action_game_app.v1.Character;
import me.kenux.playground.pattern.strategy.action_game_app.v1.strategy.AxeBehavior;
import me.kenux.playground.pattern.strategy.action_game_app.v1.strategy.KnifeBehavior;
import me.kenux.playground.pattern.strategy.action_game_app.v1.Queen;
import me.kenux.playground.pattern.strategy.action_game_app.v1.strategy.SwordBehavior;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

@Slf4j
class ActionGameV1Test {

    private KnifeBehavior knifeBehavior;
    private AxeBehavior axeBehavior;
    private SwordBehavior swordBehavior;

    @BeforeEach
    void before() {
        knifeBehavior = new KnifeBehavior();
        axeBehavior = new AxeBehavior();
        swordBehavior = new SwordBehavior();
    }

    @Test
    void test1() {
        Character queen = new Queen();
        queen.setWeaponBehavior(knifeBehavior);
        queen.fight();
        queen.setWeaponBehavior(swordBehavior);
        queen.fight();
        queen.setWeaponBehavior(axeBehavior);
        queen.fight();
    }
}
