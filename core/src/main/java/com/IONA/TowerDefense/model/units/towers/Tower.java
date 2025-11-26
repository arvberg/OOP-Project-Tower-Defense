package com.IONA.TowerDefense.model.units.towers;

import com.IONA.TowerDefense.model.units.Unit;
import com.IONA.TowerDefense.model.units.enemies.Enemy;
import com.IONA.TowerDefense.model.units.interfaces.Targetable;
import com.IONA.TowerDefense.model.units.interfaces.TargetingStrategy;
import com.IONA.TowerDefense.model.units.towers.targetingStrategies.TargetLeadingEnemyStrategy;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import java.util.List;

public abstract class Tower extends Unit {
    protected  int damage;
    protected float projectileSpeed;
    protected float fireRate;
    protected int cost;
    protected int level;
    protected int rangeRadius;
    protected int direction;

    protected String attackType;
    protected TargetingStrategy targetingStrategy;

    public Texture texture;
    private Vector2 dimension;

    public Tower(float projectileSpeed, int cost, int rangeRadius, float fireRate) {
        this.projectileSpeed = projectileSpeed;
        this.cost = cost;
        this.rangeRadius = rangeRadius;
        this.fireRate = fireRate;
        this.texture = new Texture("Tower_temp_04.png");    //PLACEHOLDER
        level = 1;
    }
    public void setTargetingStrategy(TargetingStrategy targetingStrategy) {
        this.targetingStrategy = targetingStrategy;
    }

    public List<Enemy> getTargets(List<Enemy> enemies) {
        return targetingStrategy.pick(enemies, this);
    }

    public void update() {
    }

    public void setRangeRadius(int rangeRadius) {
        this.rangeRadius = rangeRadius;
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

    public float getFireRate(){
        return fireRate;
    }

    public abstract void fire();

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public float getProjectileSpeed() {
        return projectileSpeed;
    }

    public boolean canShoot() {
        return false;
    }

    public void resetCooldown() {
    }

    public String getAttackType() {
        return attackType;
    }

    public void setAttackType(String attackType) {
        this.attackType = attackType;
    }
/*
    public boolean isClicked() {
    }

 */
}

