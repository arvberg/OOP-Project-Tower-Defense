package com.IONA.TowerDefense.model.units.towers.projectiles;

import javax.swing.*;
import java.awt.*;

public class Projectile {
    protected int attack;
    protected int speed;
    protected int direction;
    protected Point coordinate;
    protected ImageIcon projectileIcon;

    public Projectile(int attack, int speed, int direction, Point coordinate, ImageIcon projectileIcon) {
        this.attack = attack;
        this.speed = speed;
        this.direction = direction;
        this.coordinate = coordinate;
        this.projectileIcon = projectileIcon;
    }

    public int getAttack() {
        return attack;
    }

    public int getSpeed() {
        return speed;
    }

    public int getDir(){
        return direction;
    }

    public Point getCoordinate() {
        return coordinate;
    }

    public ImageIcon getIcon() {
        return projectileIcon;
    }
}
