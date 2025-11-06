package com.IONA.TowerDefense.model;

import java.awt.*;

public class TowerPlain extends Tower{
    private static final int BASE_ATTACK = 50;
    private static final int BASE_COST = 50;

    public TowerPlain(Point position, Dimension size) {
        super(position, size);
        this.attack = BASE_ATTACK;
        this.speed = 0;
        this.cost = BASE_COST;
        this.level = 1;
        this.rangeRadius = 100;
    }

    @Override
    public void attack(Targetable target, long currentTimeMillis) {
    }

    @Override
    public void fire() {
    }


    @Override
    public void move() {
    }
}
