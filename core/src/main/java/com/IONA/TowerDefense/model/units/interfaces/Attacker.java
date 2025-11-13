package com.IONA.TowerDefense.model.units.interfaces;

public interface Attacker {
    boolean canAttack();

    void attack(Targetable target);

    boolean canAttack(long currentTimeMillis);

    int getAttackDamage();

    float getAttackCooldown();
}
