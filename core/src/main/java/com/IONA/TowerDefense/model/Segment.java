package com.IONA.TowerDefense.model;

import java.awt.*;

public class Segment {

    public final Point start;
    public final int length;
    public final Direction direction;

    public Segment(Point start, int length, Direction direction) {
        this.start = start;
        this.length = length;
        this.direction = direction;
    }

    public Point getStartPoint() {
        return start;
    }

    public int getLength() {
        return length;
    }
    public Direction getDirection() {
        return direction;
    }



    public Point getEnd() {
        return switch (direction) {
            case NORTH -> new Point(start.x, start.y - length);
            case EAST -> new Point(start.x + length, start.y);
            case SOUTH -> new Point(start.x, start.y + length);
            case WEST -> new Point(start.x - length, start.y);
        };
    }


}
