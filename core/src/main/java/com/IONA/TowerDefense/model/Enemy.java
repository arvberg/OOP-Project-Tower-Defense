package com.IONA.TowerDefense.model;

import java.awt.*;

public abstract class Enemy extends Unit implements Movable {

    private int speed;

    public Enemy(Point position, Dimension size, int speed) {
        super(position, size);
        this.speed = speed;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    @Override
    public void move() {}

}
