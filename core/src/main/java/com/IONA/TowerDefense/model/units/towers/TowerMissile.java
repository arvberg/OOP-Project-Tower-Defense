package com.IONA.TowerDefense.model.units.towers;

import com.IONA.TowerDefense.model.units.interfaces.TargetingStrategy;
import com.IONA.TowerDefense.model.units.projectiles.Missile;
import com.IONA.TowerDefense.model.units.towers.attackStrategies.AreaAttackStrategy;
import com.IONA.TowerDefense.model.units.towers.attackStrategies.HomingAttackStrategy;
import com.IONA.TowerDefense.model.units.towers.attackStrategies.ProjectileAttackStrategy;
import com.IONA.TowerDefense.model.units.towers.targetingStrategies.TargetAllStrategy;
import com.IONA.TowerDefense.model.units.towers.targetingStrategies.TargetLeadingStrategy;
import com.IONA.TowerDefense.model.units.towers.targetingStrategies.TargetNearestStrategy;
import com.IONA.TowerDefense.model.units.towers.targetingStrategies.TargetStrongestStrategy;
import com.IONA.TowerDefense.model.upgrades.DamageUpgrade;
import com.IONA.TowerDefense.model.upgrades.FireRateUpgrade;
import com.IONA.TowerDefense.model.upgrades.MaxUpgrade;
import com.IONA.TowerDefense.model.upgrades.RangeUpgrade;
import com.badlogic.gdx.math.Vector2;
/**
 * TowerMissile is a homing missile tower that targets the nearest enemy.
 * <p>
 * This tower fires slow but powerful projectiles that track enemies,
 * making it effective against single high-value targets. It has a long range
 * and moderate rotation speed to aim at moving enemies.
 * <p>
 * TowerMissile supports upgrades along multiple paths, including fire rate,
 * range, and maximum damage enhancements.
 * <p>
 * The tower can attack only when it has cooled down and is properly aimed
 * at its target.
 */
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
        currentDirection = new Vector2(0, 0);
        desiredDirection = new Vector2(0, 0);
        attackStrategy = new HomingAttackStrategy();
        targetingStrategies.add(new TargetLeadingStrategy()); // The order that you add strategies is very important.
        targetingStrategies.add(new TargetNearestStrategy());
        targetingStrategies.add(new TargetStrongestStrategy());
        targetingStrategy = targetingStrategies.getFirst();
        upgradePath1.add(new RangeUpgrade(100));
        upgradePath2.add(new FireRateUpgrade(250));
        upgradePath3.add(new DamageUpgrade(80));
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

}
