package com.IONA.TowerDefense.model;

public interface Damageable {
    void takeDamage(int amount);

    int getHp();

    boolean isDead();
}
