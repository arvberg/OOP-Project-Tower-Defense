package com.IONA.TowerDefense.model;

public class WayPoint {

    private final int x;
    private final int y;
    private final int direction; // up=0, right=1, down=2, left=3

    public WayPoint(int x, int y, int direction) {
        this.x = x;
        this.y = y;
        this.direction = direction;
    }

    public int getX() {
        return x;
    }

    public int getY() { return y; }

    public int getDirection() {
        return direction;
    }
}
