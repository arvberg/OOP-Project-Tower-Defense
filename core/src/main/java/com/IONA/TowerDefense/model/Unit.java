package com.IONA.TowerDefense.model;

import java.awt.*;

public abstract class Unit {

    protected Point position;
    protected Dimension size;


    public Unit(Point position, Dimension size){
        if (position == null || size == null) throw new NullPointerException();
        this.position = new Point(position);
        this.size = size;
    }

    public Unit() {

    }

    public Dimension getSize() {
        return size;
    }

    public Rectangle getHitbox(){
        return new Rectangle(position.x, position.y, size.width, size.height);
    }

    public void setPosition(Point position){
        if (position == null){
            throw new NullPointerException();
        }
        this.position = position;
    }

    public Point getPosition() {
        return this.position;
    }

    public void move(int dx, int dy){
        position.translate(dx, dy);
    }
}

// test
