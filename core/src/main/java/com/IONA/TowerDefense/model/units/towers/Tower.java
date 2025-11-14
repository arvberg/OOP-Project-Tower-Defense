package com.IONA.TowerDefense.model.units.towers;

import com.IONA.TowerDefense.model.GameModel;
import com.IONA.TowerDefense.model.units.interfaces.Targetable;

import javax.swing.*;
import java.awt.*;

public abstract class Tower{
    protected int attack;
    protected int speed;
    protected int cost;
    protected int level;
    protected int rangeRadius;
    protected int direction;
    protected Point position;
    protected ImageIcon image;
    GameModel model;

    public Tower(int attack, int speed, int cost, int rangeRadius, GameModel model) {
        this.attack = attack;
        this.speed = speed;
        this.cost = cost;
        this.rangeRadius = rangeRadius;
        level = 1;
        this.model = model;
    }

    public int getAttack(){
        return attack;
    }

    public int getSpeed(){
        return speed;
    }

    public int getDir(){
        return direction;
    }

    public abstract void attack(Targetable target, long currentTimeMillis);

    public int getCost(){
        return cost;
    }

    public int getRange(){
        return rangeRadius;
    }

    public ImageIcon getImage(){
        return image;
    }

    public Point getPosition(){
        return position;
    }

    public void setPosition(Point position) {
        if (position == null) {
            throw new NullPointerException();
        }
        this.position = position;
    }

    public abstract void fire();
}

