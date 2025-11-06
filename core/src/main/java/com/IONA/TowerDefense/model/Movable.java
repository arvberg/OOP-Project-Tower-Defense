package com.IONA.TowerDefense.model;

import java.awt.*;

public interface Movable {
    Point getPosition();

    float getSpeed();

    void setPosition(Point position);

    void move();
}
