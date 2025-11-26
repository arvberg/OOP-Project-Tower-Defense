package com.IONA.TowerDefense.model.units.towers;

import com.IONA.TowerDefense.model.units.Unit;
import com.IONA.TowerDefense.model.units.interfaces.Targetable;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public abstract class Tower extends Unit {
    protected  int damage;
    protected int attack;
    protected float projectileSpeed;
    protected float fireRate;
    protected int cost;
    protected int level;
    protected float rangeRadius;
    protected int direction;

    public Texture texture;
    private Vector2 dimension;

    public Tower(int attack, float projectileSpeed, int cost, float rangeRadius, float fireRate) {
        this.attack = attack;
        this.projectileSpeed = projectileSpeed;
        this.cost = cost;
        this.rangeRadius = rangeRadius;
        this.fireRate = fireRate;
        this.texture = new Texture("Tower_temp_03.png");    //PLACEHOLDER
        level = 1;
    }

    public void update() {
    }

    public void setRangeRadius(int rangeRadius) {
        this.rangeRadius = rangeRadius;
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

    public float getRange(){
        return rangeRadius;
    }

    public float getFireRate(){
        return fireRate;
    }

    public abstract void fire();

    public int getDamage() {
        return damage;
    }

    public float getProjectileSpeed() {
        return projectileSpeed;
    }

    public boolean canShoot() {
        return false;
    }

    public void resetCooldown() {
    }

}

