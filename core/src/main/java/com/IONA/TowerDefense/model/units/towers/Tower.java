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
    protected String towerType;
    protected List<TargetingStrategy> targetingStrategies = new ArrayList<>();

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
    protected final Deque<TowerUpgrade> upgradePath3 = new ArrayDeque<>();

    public List<TargetingStrategy> getTargetingStrategies() {
        return this.targetingStrategies;
    }

    public String getTowerType() {
        return this.towerType;
    }

    public Deque<TowerUpgrade> getUpgradePath1() {
        return upgradePath1;
    }

    public Deque<TowerUpgrade> getUpgradePath2() {
        return upgradePath2;
    }

    public Deque<TowerUpgrade> getUpgradePath3() {
        return upgradePath3;
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

    public int getCost() {
        return cost;
    }

    public TargetingStrategy getTargetingStrategyAtIndex(int index) {
        return targetingStrategies.get(index);
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

    public float getRange() {
        return range;
    }

    public float getBaseRange() {
        return baseRange;
    }

    public float getFireRate() {
        return fireRate;
    }

    public float getBaseFireRate() {
        return baseFireRate;
    }

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

    public boolean hasCooledDown() {
        return cooldown <= 0f;
    }

    public float getCooldown() {
        return cooldown;
    }

    public void resetCooldown() {
        cooldown = fireRate;
    }

    public void updateCooldown(float delta) {
        cooldown -= delta;
    }

    public AttackStrategy getAttackStrategy() {
        return this.attackStrategy;
    }

    public void setTargetingStrategy(TargetingStrategy targetingStrategy) {
        this.targetingStrategy = targetingStrategy;
    }

    ;

    public float getBaseDamage() {
        return baseDamage;
    }
}

