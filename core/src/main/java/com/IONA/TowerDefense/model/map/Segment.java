package com.IONA.TowerDefense.model.map;

import com.IONA.TowerDefense.model.Direction;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class Segment {

    private final Vector2 start;
    private final int length;
    private final Direction direction;
    private final float width;

    public Segment(Vector2 start, int length, Direction direction) {
        this.start = start;
        this.length = length;
        this.direction = direction;
        this.width = 0.6f;

    }

    public Vector2 getStartPosition() {
        return start;
    }

    public Direction getDirection() {
        return direction;
    }

    public float getWidth() {
        return width;
    }

    public Vector2 getEnd() {
        return switch (direction) {
            case NORTH -> new Vector2(start.x, start.y + length);
            case SOUTH -> new Vector2(start.x, start.y - length);
            case EAST -> new Vector2(start.x + length, start.y);
            case WEST -> new Vector2(start.x - length, start.y);
        };
    }

    public Vector2 getEndForDraw(float w) {
        return switch (direction) {
            case NORTH -> new Vector2(start.x, start.y + length + w/2);
            case SOUTH -> new Vector2(start.x, start.y - length - w/2);
            case EAST -> new Vector2(start.x + length + w/2, start.y);
            case WEST -> new Vector2(start.x - length - w/2, start.y);
        };
    }


}
