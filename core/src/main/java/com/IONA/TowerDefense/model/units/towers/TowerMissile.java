package com.IONA.TowerDefense.model.units.towers;

import com.IONA.TowerDefense.model.units.interfaces.TargetingStrategy;
import com.IONA.TowerDefense.model.units.projectiles.Missile;
import com.IONA.TowerDefense.model.units.towers.attackStrategies.AreaAttackStrategy;
import com.IONA.TowerDefense.model.units.towers.attackStrategies.HomingAttackStrategy;
import com.IONA.TowerDefense.model.units.towers.attackStrategies.ProjectileAttackStrategy;
import com.IONA.TowerDefense.model.units.towers.targetingStrategies.TargetAllStrategy;
import com.IONA.TowerDefense.model.units.towers.targetingStrategies.TargetNearestStrategy;
import com.IONA.TowerDefense.model.upgrades.FireRateUpgrade;
import com.IONA.TowerDefense.model.upgrades.MaxUpgrade;
import com.IONA.TowerDefense.model.upgrades.RangeUpgrade;
import com.badlogic.gdx.math.Vector2;

public class TowerMissile extends Tower implements Rotatable {

    public TowerMissile() {
        dimension = new Vector2(1f, 1f);
        damage = 35;
        baseDamage = 35;
        projectileSpeed = 2;
        baseFireRate = 1f;
        cost = 50;
        fireRate = 1f;
        range = 8f;
        baseRange = 2f;
        cooldown = 0f;
        rotationSpeed = 5f;
        aimingMargin = 1f;
        currentDirection = new Vector2(0,0);
        desiredDirection = new Vector2(0,0);
        attackStrategy = new HomingAttackStrategy();
        targetingStrategy = new TargetNearestStrategy();
        upgradePath1.add(new FireRateUpgrade(1));
        upgradePath2.add(new RangeUpgrade(1));
        upgradePath2.add(new FireRateUpgrade(1));
        upgradePath2.add(new MaxUpgrade(0));
        towerType = "TowerMissile";
    }

    @Override
    public void rotateTower(float delta) {
        float r = rotationSpeed * delta;
        float xNew = currentDirection.x + (desiredDirection.x - currentDirection.x) * r;
        float yNew = currentDirection.y + (desiredDirection.y - currentDirection.y) * r;
        this.currentDirection = new Vector2(xNew, yNew).nor();
    }

    @Override
    public void setTargetingStrategy(TargetingStrategy targetingStrategy) {
        this.targetingStrategy = targetingStrategy;
    }

    @Override
    public boolean canAttack() {
        return hasCooledDown() && isAiming();
    }

    @Override
    public void setDesiredDirection(Vector2 desiredDirection) {
        this.desiredDirection = desiredDirection;
    }

    @Override
    public float getRotationSpeed() {
        return rotationSpeed;
    }
}
