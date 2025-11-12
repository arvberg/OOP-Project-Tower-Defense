package com.IONA.TowerDefense.model;

import java.awt.*;

public abstract class Tower extends Unit{
    protected int attack;
    protected int speed;
    protected int cost;
    protected int level;
    protected int rangeRadius;
    protected int cooldown;

    public Tower(Point position, Dimension size) {
        super(position, size);
        level = 1;
    }

    public int getAttack(){
        return attack;
    }

    public float getSpeed(){
        return speed;
    }

    public abstract void attack(Targetable target, long currentTimeMillis);

    public int getCost(){
        return cost;
    }

    public int getRange(){
        return rangeRadius;
    }

    public int getCooldownMs(){
        return cooldown;
    }

    public abstract void fire();
}
