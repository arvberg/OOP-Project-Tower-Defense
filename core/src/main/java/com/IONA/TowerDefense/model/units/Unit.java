package com.IONA.TowerDefense.model.units;

import com.badlogic.gdx.math.Vector2;

public abstract class Unit {
    protected Vector2 position = new Vector2();

    public Vector2 getPosition() {
        return position;
    }

    public void setPosition(Vector2 position) {
        this.position = position;
    }

    public float getX() {
        return position.x;
    }

    public float getY() {
        return position.y;
    }
}
