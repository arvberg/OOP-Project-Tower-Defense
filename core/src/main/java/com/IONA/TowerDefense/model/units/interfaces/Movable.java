package com.IONA.TowerDefense.model.units.interfaces;

import com.badlogic.gdx.math.Vector2;

public interface Movable {

    Vector2 getPosition();

    float getSpeed();

    default void setPosition(Vector2 position) {
    }

    default void move(float delta) {
    }
}
