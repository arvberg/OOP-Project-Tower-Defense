package model;

import java.awt.*;

public abstract class Unit implements UnitPosition, UnitSize {

    private Point position;
    private Dimension size;


    public Unit(Point position, Dimension size){
        this.position = new Point(position);
        this.size = size;
    }
    public Point getPosition() {
        return position;
    }
    public Dimension getSize() {
        return size;
    }

    void setPosition(Point position){
        if (position == null){
            throw new NullPointerException();
        }
        this.position = position;
    }
}