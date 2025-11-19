package com.IONA.TowerDefense.model;

import com.IONA.TowerDefense.model.units.interfaces.Renderable;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class Segment {

    public final Vector2 start;
    public final int length;
    public final Direction direction;
    public final Texture texture;

    public Segment(Vector2 start, int length, Direction direction) {
        this.start = start;
        this.length = length;
        this.direction = direction;
        this.texture = new Texture("Path_temp_brush_01.png");
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
