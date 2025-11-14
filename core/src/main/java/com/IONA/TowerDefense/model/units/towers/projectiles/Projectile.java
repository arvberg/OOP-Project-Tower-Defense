package com.IONA.TowerDefense.model.units.towers.projectiles;

import com.IONA.TowerDefense.model.GameModel;

import javax.swing.*;
import java.awt.*;
import java.util.Vector;

public class Projectile {
    protected int damage;
    protected double speed;
    protected double x;
    protected double y;
    protected double dx;
    protected double dy;
    protected ImageIcon projectileIcon;

    protected GameModel model;

    public Projectile(int damage, double speed, double x, double y, double dx, double dy, ImageIcon projectileIcon, GameModel model) {
        this.damage = damage;
        this.speed = speed;
        this.x = x;
        this.y = y;
        this.dx = dx;
        this.dy = dy;
        this.projectileIcon = projectileIcon;
        this.model = model;
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

    public int getSpeed() {
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

    public double getX() {
        return x;
    }
    public double getY() {
        return y;
    }

    public ImageIcon getIcon() {
        return projectileIcon;
    }
}
