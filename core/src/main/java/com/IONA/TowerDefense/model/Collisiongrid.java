package com.IONA.TowerDefense.model;

import java.awt.*;

public class CollisionGrid {
    protected int width;
    protected int height;
    protected int x;
    protected int y;

    public CollisionGrid(int width, int height, int x, int y) {
        this.width = width;
        this.height = height;
        this.x = x;
        this.y = y;
    }

    //TO-DO get the different types of tower to set the corresponding speed of the projectile
    public int towerAttack(Tower tower, Enemy enemy) {
        Point towerCoor = tower.getPosition();
        int eSpeed = enemy.getSpeed();
        Point eCoor = enemy.getCoor();
        Projectile projectile = new Projectile(5, 5, 1, towerCoor, eCoor, "projectileIcon")
        int pSpeed = projectile.getSpeed();
    }

    public Enemy closestEnemy(Tower tower) {}
}

