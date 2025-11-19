package com.IONA.TowerDefense.model.units.projectiles;

import com.IONA.TowerDefense.model.models.RenderData;
import com.IONA.TowerDefense.model.units.Unit;
import com.IONA.TowerDefense.model.units.enemies.Enemy;
import com.IONA.TowerDefense.model.units.interfaces.Movable;
import com.IONA.TowerDefense.model.units.interfaces.Renderable;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class Projectile extends Unit implements Movable {
    protected int damage;
    protected float speed;
    protected float x;
    protected float y;
    protected float dx;
    protected float dy;
    protected Enemy target;
    private Vector2 dimension;
    public Texture projectileIcon;

    public Projectile(int damage, float speed, float x, float y, float dx, float dy, Enemy target) {
        this.damage = damage;
        this.speed = speed;
        this.x = x;
        this.y = y;
        this.dx = dx;
        this.dy = dy;
        this.target = target;
        this.projectileIcon = new Texture("Projectile_temp_01.png");
    }

    public void setPosition(float newX, float newY) {
        this.x = newX;
        this.y = newY;
    }

    public void move() {
        x += dx * speed;
        y += dy * speed;
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
        this.x = position.x;
        this.y = position.y;
    }

    public float getDx(){
        return dx;
    }

    public float getDy(){
        return dy;
    }

    public void setDir(float newDx, float newDy) {
        this.dx = newDx;
        this.dy = newDy;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public Texture getIcon() {
        return projectileIcon;
    }
}
