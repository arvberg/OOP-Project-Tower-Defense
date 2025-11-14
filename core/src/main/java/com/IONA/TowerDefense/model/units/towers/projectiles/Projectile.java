package com.IONA.TowerDefense.model.units.towers.projectiles;

import javax.swing.*;
import java.awt.*;

public class Projectile {
    protected int attack;
    protected int speed;
    protected Point direction; //x,y 0-1
    protected Point position;
    protected Point target;
    protected ImageIcon projectileIcon;

    public Projectile(int attack, int speed, Point direction, Point position, Point target, ImageIcon projectileIcon) {
        this.attack = attack;
        this.speed = speed;
        this.direction = direction;
        this.position = position;
        this.target = target;
        this.projectileIcon = projectileIcon;
    }

    public void move(Point start, Point target, int speed) {
        int dx = start.x - target.x;
        int dy = start.y - target.y;
        int angle = dy / dx;
        int newY = dx + speed * angle;
        int newX = dx + speed / angle; //doublecheck the math here
    }

    public int getAttack() {
        return attack;
    }

    public int getSpeed() {
        return speed;
    }

    public Point getDir(){
        return direction;
    }

    public Point getPosition() {
        return position;
    }

    public ImageIcon getIcon() {
        return projectileIcon;
    }
}
