package com.IONA.TowerDefense.model.units.projectiles;

import com.IONA.TowerDefense.model.units.Unit;
import com.IONA.TowerDefense.model.units.enemies.Enemy;
import com.IONA.TowerDefense.model.units.interfaces.Movable;
import com.badlogic.gdx.math.Vector2;

/**
 * Projectile is an abstract base class for all projectiles in the game.
 * <p>
 * A projectile represents an attack object fired from a tower that moves
 * through the game world and damages enemies. The class provides shared
 * functionality for movement, position handling, damage, and lifecycle
 * management.
 */
public abstract class Projectile extends Unit implements Movable {
    protected int damage;
    protected float speed;
    protected Vector2 position;
    protected Vector2 dxdy;
    protected Enemy enemyTarget;
    protected boolean destroyed;
    private Vector2 dimension;
    protected float lifeTime = 0;
    protected float lifeSpan;

    public Projectile(int damage, float speed, Vector2 position, Vector2 dxdy) {
        this.damage = damage;
        this.speed = speed;
        this.position = position;
        this.dxdy = dxdy;
        this.destroyed = false;
    }

    public void update(float delta) {
        move(delta);
        updateLifeTime(delta);
    }

    public void updateLifeTime(float delta) {
        lifeTime += delta;
        if (lifeTime > lifeSpan) {
            setDestroyed(true);
        }
    }

    public abstract void move(float delta);

    public boolean outOfBounds(Vector2 worldDimensions) {
        boolean outOfWidth = position.x < 0 || position.x > worldDimensions.x;
        boolean outOfHeight = position.y < 0 || position.y > worldDimensions.y;
        return outOfWidth || outOfHeight;
    }

    public void setPosition(float newX, float newY) {
        this.position.x = newX;
        this.position.y = newY;
    }

    public Vector2 getPosition() {
        return new Vector2(position.x, position.y);
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public float getSpeed() {
        return speed;
    }

    @Override
    public void setPosition(Vector2 position) {
        this.position.x = position.x;
        this.position.y = position.y;
    }

    public void setDir(float newDx, float newDy) {
        this.dxdy.x = newDx;
        this.dxdy.y = newDy;
    }

    public Vector2 getDir() {
        return this.dxdy;
    }

    public Enemy getEnemyTarget() {
        return enemyTarget;
    }

    public void setEnemyTarget(Enemy enemyTarget) {
        this.enemyTarget = enemyTarget;
    }

    public void setDestroyed(boolean destroyed) {
        this.destroyed = destroyed;
    }

    public boolean isDestroyed() {
        return destroyed;
    }

}
