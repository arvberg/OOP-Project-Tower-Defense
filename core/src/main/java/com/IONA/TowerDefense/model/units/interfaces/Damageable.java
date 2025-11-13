package com.IONA.TowerDefense.model.units.interfaces;

public interface Damageable {
    void takeDamage(int amount);

    int getHp();

    boolean isDead();
}
