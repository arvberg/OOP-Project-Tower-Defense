package com.IONA.TowerDefense.model.units.towers;

import com.badlogic.gdx.math.Vector2;

public interface Rotatable {
    public void rotateTower(float delta);
    public float getRotationSpeed();
    public boolean isAiming();
    public void setDesiredDirection(Vector2 desiredDirection);
}
