package com.IONA.TowerDefense.model;

import com.IONA.TowerDefense.model.units.enemies.Enemy;
import com.IONA.TowerDefense.model.units.towers.Tower;

public class CollisionGrid {
    protected int width;
    protected int height;
    protected int x;
    protected int y;

    public void Collisiongrid(int width, int height, int x, int y) {
        this.width = width;
        this.height = height;
        this.x = x;
        this.y = y;
    }

    public boolean withinRadius(Enemy enemy, Tower tower) {
        return false; //placeholder
    }
}

