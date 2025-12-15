package com.IONA.TowerDefense.model.units.projectiles;

import com.IONA.TowerDefense.HeartBeat;
import com.IONA.TowerDefense.model.units.Unit;
import com.IONA.TowerDefense.model.units.enemies.Enemy;
import com.IONA.TowerDefense.model.units.interfaces.Movable;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class ProjectileBasic extends Projectile implements Movable {
    protected int damage;
    protected float speed;
    protected Vector2 position;
    protected Vector2 dxdy;
    protected Enemy enemyTarget;
    protected boolean destroyed;
    private Vector2 dimension;
    private String projectileType = "Basic";

    public ProjectileBasic (int damage, float speed, Vector2 position, Vector2 dxdy) {
        super(damage,speed,position,dxdy);
        this.damage = damage;
        this.speed = speed;
        this.position = position;
        this.dxdy = dxdy;
        this.destroyed = false;
    }

    public void setPosition(float newX, float newY) {
        this.position.x = newX;
        this.position.y = newY;
    }

    @Override
    public void move(float delta) {
        position.x += dxdy.x * speed * delta;
        position.y += dxdy.y * speed * delta;
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

    public float getX() {
        return position.x;
    }

    public float getY() {
        return position.y;
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

    public String getProjectileType() {
        return projectileType;
    }
}
