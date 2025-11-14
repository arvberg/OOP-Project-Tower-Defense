package com.IONA.TowerDefense.model.units.towers.projectiles;

import com.badlogic.gdx.graphics.Texture;

import java.awt.*;

public class Projectile {
    protected int damage;
    protected double speed;
    protected double x;
    protected double y;
    protected double dx;
    protected double dy;
    protected Texture projectileIcon;

    public Projectile(int damage, double speed, double x, double y, double dx, double dy) {
        this.damage = damage;
        this.speed = speed;
        this.x = x;
        this.y = y;
        this.dx = dx;
        this.dy = dy;
        this.projectileIcon = new Texture("ProtTower.png");
    }

    public void setPosition(double newX, double newY) {
        this.x = newX;
        this.y = newY;
    }

    public void move(Point direction, int speed) {
        x += dx * speed;
        y += dy * speed;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public double getSpeed() {
        return speed;
    }

    public double getDx(){
        return dx;
    }

    public double getDy(){
        return dy;
    }

    public void setDir(double newDx, double newDy) {
        this.dx = newDx;
        this.dy = newDy;
    }

    public float getX() {
        return (float) x;
    }
    public float getY() {
        return (float) y;
    }

    public Texture getIcon() {
        return projectileIcon;
    }
}
