package com.IONA.TowerDefense.model;

public interface Attacker {
    boolean canAttack();

    void attack(Targetable target);

    boolean canAttack(long currentTimeMillis);

    int getAttackDamage();

    float getAttackCooldown();
}
