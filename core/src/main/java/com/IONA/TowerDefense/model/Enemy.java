package com.IONA.TowerDefense.model;

import java.awt.*;

public abstract class Enemy extends Unit implements Movable {

    private int speed;

    public Enemy(Point position, Dimension size, int speed) {
        this.speed = speed;
        super(position, size);
    }

    abstract public int getSpeed();
}
