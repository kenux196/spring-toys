package me.kenux.designpattern.strategy.code;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
public class GameCharacter {

    private Weapon weapon;

    public void changeWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    public void attack() {
        if (weapon == null) {
            log.info("맨손 공격");
        } else {
            weapon.attack(); // 위임
        }
    }

}