package com.IONA.TowerDefense.model.units.towers;

import com.IONA.TowerDefense.VectorUtils;
import com.IONA.TowerDefense.model.units.Unit;
import com.IONA.TowerDefense.model.units.enemies.Enemy;
import com.IONA.TowerDefense.model.units.interfaces.TargetingStrategy;
import com.IONA.TowerDefense.model.units.towers.attackStrategies.AttackStrategy;
import com.IONA.TowerDefense.model.upgrades.TowerUpgrade;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;
import java.util.Vector;

public abstract class Tower extends Unit {

    protected int damage;
    protected int baseDamage;
    protected float projectileSpeed;
    protected float fireRate;
    protected float baseFireRate;
    protected int cost;
    protected float range;
    protected float baseRange;
    protected float cooldown;

    // rotation properties
    protected Vector2 desiredDirection;
    protected Vector2 currentDirection;
    float rotationSpeed;
    protected float aimingMargin;


    protected boolean hasCurrentUpgradeMenu = false;
    protected Vector2 dimension;

    public abstract boolean canAttack();

    public void update(float delta) {
        updateCooldown(delta);
    }

    public float getAngleDeg() {
        return VectorUtils.angleFromDirection(currentDirection);
    }

    protected TargetingStrategy targetingStrategy;
    protected AttackStrategy attackStrategy;

    protected final Deque<TowerUpgrade> upgradePath1 = new ArrayDeque<>();
    protected final Deque<TowerUpgrade> upgradePath2 = new ArrayDeque<>();

    public void setHasCurrentUpgradeMenu(boolean b){this.hasCurrentUpgradeMenu=b;}

    public boolean getHasCurrentUpgradeMenu(){return this.hasCurrentUpgradeMenu;}

    public Deque<TowerUpgrade> getUpgradePath1() {
        return upgradePath1;
    }

    public Deque<TowerUpgrade> getUpgradePath2() {
        return upgradePath2;
    }


    public List<Enemy> getTargets(List<Enemy> enemies) {
        return targetingStrategy.pick(enemies, this);
    }

    public TargetingStrategy getTargetingStrategy() {
        return targetingStrategy;
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

    public boolean isAiming() {
        float deltaDirection = VectorUtils.distance(currentDirection, desiredDirection);
        return deltaDirection < aimingMargin;
    }

    public Vector2 getCurrentDirection() {
        return currentDirection;
    }

    public Vector2 getDesiredDirection() {
        return desiredDirection;
    }

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

    public void setDesiredDirection(Vector2 desiredDirection) {
        this.desiredDirection = desiredDirection;
    }

    public void setCurrentDirection(Vector2 currentDirection) {
        this.currentDirection = currentDirection;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public float getProjectileSpeed() {
        return projectileSpeed;
    }

    public void setFireRate(float fireRate) {
        this.fireRate = fireRate;
    }

    public float getRotationSpeed() {
        return rotationSpeed;
    }

    public boolean hasCooledDown() {
        return cooldown <= 0f;
    }

    public float getCooldown() {
        return cooldown;
    }

    public void resetCooldown() {
        cooldown = fireRate;
    }

    public void updateCooldown(float delta){
        cooldown -= delta;
    }

    public void setAimingMargin(float aimingMargin) {
        this.aimingMargin = aimingMargin;
    }

    public AttackStrategy getAttackStrategy() {
        return this.attackStrategy;
    }

    public void setAttackStrategy(AttackStrategy attackStrategy) {
        this.attackStrategy = attackStrategy;
    }

    public abstract void setTargetingStrategy(TargetingStrategy targetingStrategy);

    public float getBaseDamage() {
        return baseDamage;
    }
}

