package com.IONA.TowerDefense.model.units.projectiles;

import com.IONA.TowerDefense.HeartBeat;
import com.IONA.TowerDefense.model.units.Unit;
import com.IONA.TowerDefense.model.units.enemies.Enemy;
import com.IONA.TowerDefense.model.units.interfaces.Movable;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class Projectile extends Unit implements Movable {
    protected int damage;
    protected float speed;
    protected Vector2 position;
    protected Vector2 dxdy;
    protected float x;
    protected float y;
    protected float dx;
    protected float dy;
    protected Enemy enemyTarget;
    protected boolean hit;
    private Vector2 dimension;
    public Texture projectileIcon;

    public Projectile(int damage, float speed, Vector2 position, Vector2 dxdy) {
        this.damage = damage;
        this.speed = speed;
        this.position = position;
        this.dxdy = dxdy;
        this.projectileIcon = new Texture("Projectile_temp_02.png");
    }

    public void setPosition(float newX, float newY) {
        this.position.x = newX;
        this.position.y = newY;
    }

    public void move() {
        float delta = HeartBeat.delta;
        x += dxdy.x * speed * delta;
        y += dxdy.y * speed * delta;
    }

    public void homingMove(float dirX, float dirY) {
        float delta = HeartBeat.delta;
        x += dirX * speed * delta;
        y += dirY * speed * delta;
    }

    public Vector2 getPosition() {
        return new Vector2(x, y);
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

    public Texture getIcon() {
        return projectileIcon;
    }

    public Enemy getEnemyTarget() {
        return enemyTarget;
    }

    public void setEnemyTarget(Enemy enemyTarget) {
        this.enemyTarget = enemyTarget;
    }

    public void setHit(boolean hit) {
        this.hit = hit;
    }

    public boolean isHit() {
        return hit;
    }
}
