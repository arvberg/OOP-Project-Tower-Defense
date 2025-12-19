package com.IONA.TowerDefense.model.units.towers;

import com.IONA.TowerDefense.model.units.interfaces.TargetingStrategy;
import com.IONA.TowerDefense.model.units.towers.attackStrategies.AreaAttackStrategy;
import com.IONA.TowerDefense.model.units.towers.attackStrategies.HomingAttackStrategy;
import com.IONA.TowerDefense.model.units.towers.attackStrategies.ProjectileAttackStrategy;
import com.IONA.TowerDefense.model.units.towers.targetingStrategies.TargetAllStrategy;
import com.IONA.TowerDefense.model.units.towers.targetingStrategies.TargetNearestStrategy;
import com.IONA.TowerDefense.model.upgrades.FireRateUpgrade;
import com.IONA.TowerDefense.model.upgrades.MaxUpgrade;
import com.IONA.TowerDefense.model.upgrades.RangeUpgrade;
import com.badlogic.gdx.math.Vector2;

public class TowerMissile extends Tower{

    public TowerMissile() {
        dimension = new Vector2(1f, 1f);
        damage = 1;
        projectileSpeed = 2;
        baseFireRate = 0.1f;
        cost = 50;
        fireRate = 0.1f;
        range = 8f;
        baseRange = 2f;
        cooldown = 0f;
        rotationSpeed = 5f;
        aimingMargin = 0.01f;
        attackStrategy = new HomingAttackStrategy();
        targetingStrategy = new TargetNearestStrategy();
        upgradePath1.add(new FireRateUpgrade(1));
        upgradePath2.add(new RangeUpgrade(1));
        upgradePath2.add(new FireRateUpgrade(1));
        upgradePath2.add(new MaxUpgrade(0));
        towerType = "TowerMissile";
    }


}
