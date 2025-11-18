package com.IONA.TowerDefense.model;

import com.badlogic.gdx.math.Vector2;

public class Segment {

    public final Vector2 start;
    public final int length;
    public final Direction direction;

    public Segment(Vector2 start, int length, Direction direction) {
        this.start = start;
        this.length = length;
        this.direction = direction;
    }

    public Vector2 getStartPosition() {
        return start;
    }

    public int getLength() {
        return length;
    }
    public Direction getDirection() {
        return direction;
    }

    public Vector2 getEnd() {
        return switch (direction) {
            case NORTH -> new Vector2(start.x, start.y + length);
            case SOUTH -> new Vector2(start.x, start.y - length);
            case EAST -> new Vector2(start.x + length, start.y);
            case WEST -> new Vector2(start.x - length, start.y);
        };
    }
}
