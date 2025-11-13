package com.IONA.TowerDefense.model.units.interfaces;

import com.IONA.TowerDefense.model.Targetable;

public interface Attacker {
    boolean canAttack();

    void attack(Targetable target);

    boolean canAttack(long currentTimeMillis);

    int getAttackDamage();

    float getAttackCooldown();
}
