package com.IONA.TowerDefense.model.units.towers;

import com.IONA.TowerDefense.model.units.interfaces.TargetingStrategy;
import com.IONA.TowerDefense.model.units.towers.attackStrategies.AreaAttackStrategy;
import com.IONA.TowerDefense.model.units.towers.attackStrategies.ProjectileAttackStrategy;
import com.IONA.TowerDefense.model.units.towers.targetingStrategies.TargetAllStrategy;
import com.IONA.TowerDefense.model.units.towers.targetingStrategies.TargetNearestStrategy;
import com.IONA.TowerDefense.model.upgrades.DamageUpgrade;
import com.IONA.TowerDefense.model.upgrades.FireRateUpgrade;
import com.IONA.TowerDefense.model.upgrades.MaxUpgrade;
import com.IONA.TowerDefense.model.upgrades.RangeUpgrade;
import com.badlogic.gdx.math.Vector2;

public class TowerPulse extends Tower {

    public TowerPulse() {
        dimension = new Vector2(1f, 1f);
        damage = 20;
        baseDamage = 20;
        projectileSpeed = 8;
        baseFireRate = 0.1f;
        cost = 50;
        fireRate = 0.5f;
        range = 2f;
        baseRange = 2f;
        rotationSpeed = 0f;
        currentDirection = new Vector2(0, 0);
        desiredDirection = new Vector2(0, 0);
        cooldown = 0f;
        targetingStrategies.add(new TargetAllStrategy());
        targetingStrategy = targetingStrategies.getFirst();
        upgradePath1.add(new RangeUpgrade(175));
        upgradePath2.add(new FireRateUpgrade(175));
        upgradePath3.add(new DamageUpgrade(200));
        towerType = "TowerPulse";
    }

    @Override
    public void setTargetingStrategy(TargetingStrategy targetingStrategy) {
        this.targetingStrategy = targetingStrategy;
    }

    @Override
    public boolean canAttack() {
        return hasCooledDown();
    }

}
