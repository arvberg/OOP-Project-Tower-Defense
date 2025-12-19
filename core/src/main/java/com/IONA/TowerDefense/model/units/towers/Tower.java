package com.IONA.TowerDefense.model.units.towers;

import com.IONA.TowerDefense.VectorUtils;
import com.IONA.TowerDefense.model.units.Unit;
import com.IONA.TowerDefense.model.units.enemies.Enemy;
import com.IONA.TowerDefense.model.units.interfaces.TargetingStrategy;
import com.IONA.TowerDefense.model.units.towers.attackStrategies.AttackStrategy;
import com.IONA.TowerDefense.model.upgrades.TowerUpgrade;
import com.badlogic.gdx.math.Vector2;

import java.util.*;

public abstract class Tower extends Unit {
    protected int damage;
    protected int baseDamage;
    protected float projectileSpeed;
    protected float fireRate;
    protected float baseFireRate;
    protected float rotationSpeed;
    protected int cost;
    protected float range;
    protected float baseRange;
    protected float cooldown;
    protected float aimingMargin;
    protected Enemy currentTarget;
    protected Vector2 direction = new Vector2(0,0);
    protected float angleDeg;
    protected boolean hasDetected;
    protected boolean isAiming;
    protected boolean hasCurrentUpgradeMenu = false;
    protected String towerType;
    protected List<TargetingStrategy> targetingStrategies = new ArrayList<>();

    protected Vector2 dimension;

    /*
    update
    if hasDetected
        if rotating
            setDesiredAngle
                if | angle-desiredangle | < 0.1
                    setIsAiming = true
                else
        if

     */

    protected TargetingStrategy targetingStrategy;
    protected AttackStrategy attackStrategy;

    protected final Deque<TowerUpgrade> upgradePath1 = new ArrayDeque<>();
    protected final Deque<TowerUpgrade> upgradePath2 = new ArrayDeque<>();
    protected final Deque<TowerUpgrade> upgradePath3 = new ArrayDeque<>();

    public List<TargetingStrategy> getTargetingStrategies(){
        return this.targetingStrategies;
    }

    public String getTowerType(){ return this.towerType; }

    public void setHasCurrentUpgradeMenu(boolean b){this.hasCurrentUpgradeMenu=b;}

    public boolean getHasCurrentUpgradeMenu(){return this.hasCurrentUpgradeMenu;}

    public Deque<TowerUpgrade> getUpgradePath1() {
        return upgradePath1;
    }

    public Deque<TowerUpgrade> getUpgradePath2() {
        return upgradePath2;
    }

    public Deque<TowerUpgrade> getUpgradePath3() {return upgradePath3;}

    public float getAngleDeg() {
        return VectorUtils.angleFromDirection(direction);
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

    public void setCost(int cost){
        this.cost = cost;
    }

    public int getCost(){
        return cost;
    }

    public TargetingStrategy getTargetingStrategyAtIndex(int index){return targetingStrategies.get(index);}

    public TargetingStrategy getCurrentTargetingStrategy(){return this.targetingStrategy;}

    public float getRange(){
        return range;
    }

    public float getBaseRange(){
        return baseRange;
    }

    public float getFireRate(){
        return fireRate;
    }

    public float getBaseFireRate() { return baseFireRate; }

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

    public Vector2 getDirection() {
        return direction;
    }

    public boolean isAimingAtCurrentTarget(Enemy enemy) {
        return true; // change, continue here
    }

    public boolean getIsAiming() {
        return isAiming;
    }

    public float getAimingMargin() {
        return aimingMargin;
    }

    public void setAimingMargin(float margin) {
        this.aimingMargin = margin;
    }

    public void setIsAiming(boolean isAiming) {
        this.isAiming = isAiming;
    }

    public void setDirection(Vector2 direction) {
        this.direction = direction;
    }

    public void setCooldown(float cooldown) {
        this.cooldown = cooldown;
    }

    public float getCooldown() {
        return cooldown;
    }

    public float getRotationSpeed() {
        return rotationSpeed;
    }

    public boolean hasCooledDown() {
        return cooldown <= 0f;
    }

    public void resetCooldown() {
        cooldown = fireRate;
    }

    public void updateCooldown(float delta){
        cooldown -= delta;
    }

    public AttackStrategy getAttackStrategy() {
        return this.attackStrategy;
    }

    public void setAttackStrategy(AttackStrategy attackStrategy) {
        this.attackStrategy = attackStrategy;
    }

    public void setTargetingStrategy(TargetingStrategy targetingStrategy){
        this.targetingStrategy = targetingStrategy;
    };
}

