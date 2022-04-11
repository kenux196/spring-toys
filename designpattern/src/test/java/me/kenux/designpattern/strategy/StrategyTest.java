package me.kenux.designpattern.strategy;

import lombok.extern.slf4j.Slf4j;
import me.kenux.designpattern.strategy.code.GameCharacter;
import me.kenux.designpattern.strategy.code.Gun;
import me.kenux.designpattern.strategy.code.Knife;
import me.kenux.designpattern.strategy.code.Sword;
import org.junit.jupiter.api.Test;

@Slf4j
public class StrategyTest {

    @Test
    void test1() {
        GameCharacter character = new GameCharacter();
        character.attack();

        character.changeWeapon(new Knife());
        character.attack();

        character.changeWeapon(new Sword());
        character.attack();
    }

    @Test
    void test2() {
        GameCharacter character = new GameCharacter();
        character.changeWeapon(new Gun());
        character.attack();
    }
}