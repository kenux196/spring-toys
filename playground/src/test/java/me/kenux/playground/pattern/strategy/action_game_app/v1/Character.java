package me.kenux.playground.pattern.strategy.action_game_app.v1;

import me.kenux.playground.pattern.strategy.action_game_app.v1.strategy.WeaponBehavior;

public abstract class Character {

    protected WeaponBehavior weaponBehavior;

    public abstract void fight();

    public void setWeaponBehavior(WeaponBehavior weaponBehavior) {
        this.weaponBehavior = weaponBehavior;
    }
}
