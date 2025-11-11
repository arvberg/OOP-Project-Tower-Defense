package com.IONA.TowerDefense.model;

public class WayPoint {

    private double x, y;
    private final double direction; // up=0, right=1, down=2, left=3

    public WayPoint(double x, double y, double direction) {
        this.x = x;
        this.y = y;
        this.direction = direction;
    }

    public double getDirection() {
        return direction;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
}
