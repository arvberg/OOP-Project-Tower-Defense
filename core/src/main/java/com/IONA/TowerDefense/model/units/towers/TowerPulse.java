package com.IONA.TowerDefense.model.units.towers;

import com.IONA.TowerDefense.model.units.interfaces.TargetingStrategy;
import com.IONA.TowerDefense.model.units.towers.attackStrategies.AreaAttackStrategy;
import com.IONA.TowerDefense.model.units.towers.attackStrategies.ProjectileAttackStrategy;
import com.IONA.TowerDefense.model.units.towers.targetingStrategies.TargetAllStrategy;
import com.IONA.TowerDefense.model.units.towers.targetingStrategies.TargetNearestStrategy;
import com.IONA.TowerDefense.model.upgrades.FireRateUpgrade;
import com.IONA.TowerDefense.model.upgrades.MaxUpgrade;
import com.IONA.TowerDefense.model.upgrades.RangeUpgrade;
import com.badlogic.gdx.math.Vector2;

public class TowerPulse extends Tower{

    public TowerPulse() {
        dimension = new Vector2(1f, 1f);
        damage = 35;
        projectileSpeed = 8;
        baseFireRate = 0.1f;
        cost = 50;
        fireRate = 0.5f;
        range = 2f;
        baseRange = 2f;
        rotationSpeed = 0f;
        aimingMargin = 1f;
        cooldown = 0f;
        attackStrategy = new AreaAttackStrategy();
        targetingStrategy = new TargetAllStrategy();
        upgradePath1.add(new FireRateUpgrade(1));
        upgradePath2.add(new RangeUpgrade(1));
        upgradePath2.add(new FireRateUpgrade(1));
        upgradePath2.add(new MaxUpgrade(0));
        towerType = "TowerPulse";
    }

    @Override
    public void setTargetingStrategy(TargetingStrategy targetingStrategy) {
        // Ska inte användas. Pulse-tower ska endast kunna skjuta på ett sätt
    }
}
