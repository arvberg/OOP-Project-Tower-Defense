package com.IONA.TowerDefense.model;

import java.awt.*;

public abstract class Enemy extends Unit{
    public Enemy(Point position, Dimension size) {
        super(position, size);
    }
    protected int hp;
    protected int speed;
    protected int value;
    protected Rectangle hitbox;
}
