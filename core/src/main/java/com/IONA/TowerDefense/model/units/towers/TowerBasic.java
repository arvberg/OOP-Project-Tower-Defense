package com.IONA.TowerDefense.model.units.towers;

import com.IONA.TowerDefense.VectorUtils;
import com.IONA.TowerDefense.model.units.interfaces.TargetingStrategy;
import com.IONA.TowerDefense.model.units.towers.attackStrategies.AreaAttackStrategy;
import com.IONA.TowerDefense.model.units.towers.attackStrategies.ProjectileAttackStrategy;
import com.IONA.TowerDefense.model.units.towers.targetingStrategies.TargetAllStrategy;
import com.IONA.TowerDefense.model.units.towers.targetingStrategies.TargetLeadingStrategy;
import com.IONA.TowerDefense.model.units.towers.targetingStrategies.TargetNearestStrategy;
import com.IONA.TowerDefense.model.upgrades.FireRateUpgrade;
import com.IONA.TowerDefense.model.upgrades.MaxUpgrade;
import com.IONA.TowerDefense.model.upgrades.RangeUpgrade;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class TowerBasic extends Tower implements Rotatable {

    public TowerBasic() {
        dimension = new Vector2(0.8f, 0.8f);
        damage = 5;
        projectileSpeed = 8;
        baseFireRate = 0.01f;
        cost = 50;
        fireRate = 0.55f;
        range = 2f;
        baseRange = 2f;
        cooldown = 0f;
        rotationSpeed = 20f;
        aimingMargin = 1f;
        currentDirection = new Vector2(0,0);
        desiredDirection = new Vector2(0,0);
        attackStrategy = new ProjectileAttackStrategy();
        targetingStrategy = new TargetLeadingStrategy();
        upgradePath1.add(new FireRateUpgrade(1));
        upgradePath2.add(new RangeUpgrade(1));
        upgradePath2.add(new FireRateUpgrade(1));
        upgradePath2.add(new MaxUpgrade(0));

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
    public void setTargetingStrategy(TargetingStrategy targetingStrategy) {
        this.targetingStrategy = targetingStrategy;
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




