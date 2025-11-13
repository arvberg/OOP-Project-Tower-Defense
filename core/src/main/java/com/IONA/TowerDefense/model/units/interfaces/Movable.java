package com.IONA.TowerDefense.model.units.interfaces;

import java.awt.*;

public interface Movable {
    Point getPosition();

    int getSpeed();

    void setPosition(Point position);

    void move();
}
