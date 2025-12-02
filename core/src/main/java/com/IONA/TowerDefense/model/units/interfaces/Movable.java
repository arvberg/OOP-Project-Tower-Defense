package com.IONA.TowerDefense.model.units.interfaces;

import com.badlogic.gdx.math.Vector2;

import java.awt.*;

public interface Movable {

    Vector2 getPosition();

    float getSpeed();

    void setPosition(Vector2 position);

    void move(float delta);
}
