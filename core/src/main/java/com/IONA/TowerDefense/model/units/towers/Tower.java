package com.IONA.TowerDefense.model.units.towers;

import com.IONA.TowerDefense.HeartBeat;
import com.IONA.TowerDefense.model.units.Unit;
import com.IONA.TowerDefense.model.units.enemies.Enemy;
import com.IONA.TowerDefense.model.units.interfaces.Targetable;
import com.IONA.TowerDefense.model.units.interfaces.TargetingStrategy;
import com.IONA.TowerDefense.model.units.towers.targetingStrategies.TargetLeadingEnemyStrategy;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import java.util.List;
import java.util.Vector;

public abstract class Tower extends Unit {
    protected int damage;
    protected float projectileSpeed;
    protected float fireRate;
    protected int cost;
    protected float range;
    protected float cooldown;
    protected Enemy currentTarget;
    protected float dx;
    protected float dy;
    protected float angleDeg;
    protected boolean hasDetected;

    protected Vector2 dimension;

    protected String attackType;
    protected TargetingStrategy targetingStrategy;


    public void setAngleDeg(float angleDeg){this.angleDeg = angleDeg;}

    public float getAngleDeg(){return this.angleDeg;}

    public boolean isAiming() {
        return this.currentTarget != null;
    }

    public boolean getHasDetected() {
        return hasDetected;
    }

    public void setHasDetected(boolean hasDetected) {
        this.hasDetected = hasDetected;
    }

    public void setCurrentTarget(Enemy enemy){this.currentTarget = enemy;}

    public Enemy getCurrentTarget(){
            return this.currentTarget;
    }

    public void setTargetingStrategy(TargetingStrategy targetingStrategy) {
        this.targetingStrategy = targetingStrategy;
    }

    public List<Enemy> getTargets(List<Enemy> enemies) {
        return targetingStrategy.pick(enemies, this);
    }

    public void setDimension(Vector2 dimension) {
        this.dimension = dimension;
    }

    public Vector2 getDimension() {
        return dimension;
    }

    public void setRange(float range) {
        this.range = range;
    }

    public abstract void attack(Targetable target, long currentTimeMillis);

    public void setCost(int cost){
        this.cost = cost;
    }

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

    public void setProjectileSpeed(float projectileSpeed) {
        this.projectileSpeed = projectileSpeed;
    }

    public float getProjectileSpeed() {
        return projectileSpeed;
    }

    public void setFireRate(float fireRate) {
        this.fireRate = fireRate;
    }

    public void setCooldown(float cooldown) {
        this.cooldown = cooldown;
    }

    public float getCooldown() {
        return cooldown;
    }

    public String getAttackType() {
        return attackType;
    }

    public void setAttackType(String attackType) {
        this.attackType = attackType;
    }

    public boolean canShoot() {
        return cooldown <= 0f;
    }

    public void resetCooldown() {
        cooldown = fireRate;
    }

    public void update(float delta){
        cooldown -= delta;
    }


}

