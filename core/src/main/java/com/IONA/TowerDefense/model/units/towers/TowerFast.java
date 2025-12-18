package com.IONA.TowerDefense.model.units.towers;

import com.IONA.TowerDefense.model.units.interfaces.TargetingStrategy;
import com.IONA.TowerDefense.model.units.towers.targetingStrategies.TargetLeadingStrategy;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class TowerFast extends Tower implements Rotatable{

    public TowerFast() {
        dimension = new Vector2(1f, 1f);
        damage = 50;
        projectileSpeed = 8;
        cost = 50;
        fireRate = 0.1f;
        baseFireRate = 0.1f;
        range = 2;
        cooldown = 0f;
        targetingStrategy = new TargetLeadingStrategy();
        targetingStrategy = new TargetLeadingStrategy();
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
