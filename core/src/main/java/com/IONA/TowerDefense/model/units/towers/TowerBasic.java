package com.IONA.TowerDefense.model.units.towers;

import com.IONA.TowerDefense.model.units.interfaces.TargetingStrategy;
import com.IONA.TowerDefense.model.units.towers.attackStrategies.ProjectileAttackStrategy;
import com.IONA.TowerDefense.model.units.towers.targetingStrategies.TargetLeadingStrategy;
import com.IONA.TowerDefense.model.units.towers.targetingStrategies.TargetNearestStrategy;
import com.IONA.TowerDefense.model.upgrades.RangeUpgrade;
import com.badlogic.gdx.math.Vector2;

/**
 * Concrete implementation of a basic tower in the game.
 * <p>
 * TowerBasic is a standard projectile tower with moderate damage,
 * range, and fire rate. It supports multiple targeting strategies,
 * such as leading and nearest targeting, and can rotate smoothly
 * towards its current target.
 * <p>
 * The tower also supports upgrades through predefined upgrade paths
 * that can improve range, fire rate, or other attributes.
 * <p>
 * The tower can attack only when it has cooled down and is properly
 * aimed at a target.
 */
public class TowerBasic extends Tower implements Rotatable {

    public TowerBasic() {
        dimension = new Vector2(0.8f, 0.8f);
        damage = 5;
        baseDamage = 5;
        projectileSpeed = 8;
        baseFireRate = 0.01f;
        cost = 50;
        fireRate = 0.55f;
        range = 2f;
        baseRange = 2f;
        cooldown = 0f;
        rotationSpeed = 20f;
        aimingMargin = 0.5f;
        currentDirection = new Vector2(0, 0);
        desiredDirection = new Vector2(0, 0);
        attackStrategy = new ProjectileAttackStrategy();
        targetingStrategies.add(new TargetLeadingStrategy()); // The order that you add strategies is very important.
        targetingStrategies.add(new TargetNearestStrategy());
        targetingStrategy = targetingStrategies.getFirst();
        upgradePath1.add(new RangeUpgrade(40));
        upgradePath2.add(new RangeUpgrade(40));
        upgradePath3.add(new RangeUpgrade(40));
        towerType = "TowerBasic";
    }

    @Override
    public void setTargetingStrategy(TargetingStrategy targetingStrategy) {
        this.targetingStrategy = targetingStrategy;
    }

    @Override
    public void rotateTower(float delta) {
        float r = rotationSpeed * delta;
        float xNew = currentDirection.x + (desiredDirection.x - currentDirection.x) * r;
        float yNew = currentDirection.y + (desiredDirection.y - currentDirection.y) * r;
        this.currentDirection = new Vector2(xNew, yNew).nor();
    }

    @Override
    public boolean canAttack() {
        return hasCooledDown() && isAiming();
    }

    @Override
    public void setDesiredDirection(Vector2 desiredDirection) {
        this.desiredDirection = desiredDirection;
    }

    public TargetingStrategy getTargetingStrategy() {
        return targetingStrategy;
    }
}




