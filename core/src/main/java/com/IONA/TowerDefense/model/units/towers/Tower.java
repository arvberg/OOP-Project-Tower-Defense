package com.IONA.TowerDefense.model.units.towers;

import com.IONA.TowerDefense.model.models.RenderData;
import com.IONA.TowerDefense.model.units.Unit;
import com.IONA.TowerDefense.model.units.interfaces.Renderable;
import com.IONA.TowerDefense.model.units.interfaces.Targetable;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import javax.swing.*;

public abstract class Tower extends Unit {
    protected  int damage;
    protected int attack;
    protected int projectileSpeed;
    protected int fireFrequency;
    protected int cost;
    protected int level;
    protected int rangeRadius;
    protected int direction;
    public Texture texture;
    private Vector2 dimension;


    public Tower(int attack, int projectileSpeed, int cost, int rangeRadius) {
        this.attack = attack;
        this.projectileSpeed = projectileSpeed;
        this.cost = cost;
        this.rangeRadius = rangeRadius;
        level = 1;
    }

    public int getAttack(){
        return attack;
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

    public abstract void fire();

    public int getDamage() {
        return damage;
    }

    public float getProjectileSpeed() {
        return projectileSpeed;
    }
}

