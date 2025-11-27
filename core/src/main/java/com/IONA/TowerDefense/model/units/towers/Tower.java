package com.IONA.TowerDefense.model.units.towers;

import com.IONA.TowerDefense.model.units.Unit;
import com.IONA.TowerDefense.model.units.enemies.Enemy;
import com.IONA.TowerDefense.model.units.interfaces.Targetable;
import com.IONA.TowerDefense.model.units.interfaces.TargetingStrategy;
import com.IONA.TowerDefense.model.units.towers.targetingStrategies.TargetLeadingEnemyStrategy;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import java.util.List;

public abstract class Tower extends Unit {
    protected  int damage;
    protected float projectileSpeed;
    protected float fireRate;
    protected int cost;
    protected int level;
    protected float range;
    protected int direction;

    protected String attackType;
    protected TargetingStrategy targetingStrategy;

    public Texture texture;
    public TextureRegion rangeTexture;
    private Vector2 dimension;

    public Tower(float projectileSpeed, int cost, float range, float fireRate) {
        this.projectileSpeed = projectileSpeed;
        this.cost = cost;
        this.range = range;
        this.fireRate = fireRate;
        this.texture = new Texture("Tower_temp_04.png");    //PLACEHOLDER
        this.rangeTexture = new TextureRegion(new Texture("Range_01.png"));
        level = 1;
    }
    public void setTargetingStrategy(TargetingStrategy targetingStrategy) {
        this.targetingStrategy = targetingStrategy;
    }

    public List<Enemy> getTargets(List<Enemy> enemies) {
        return targetingStrategy.pick(enemies, this);
    }

    public TextureRegion getRangeTexture() {
        return rangeTexture;
    }

    public void update() {
    }

    public void setrange(float range) {
        this.range = range;
    }

    public int getDir(){
        return direction;
    }

    public abstract void attack(Targetable target, long currentTimeMillis);

    public int getCost(){
        return cost;
    }

    public float getRange(){
        return range;
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

