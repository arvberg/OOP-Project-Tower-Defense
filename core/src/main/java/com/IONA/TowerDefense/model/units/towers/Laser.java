package com.IONA.TowerDefense.model.units.towers;

import com.IONA.TowerDefense.model.Direction;
import com.badlogic.gdx.math.Vector2;

public class Laser {

    private Vector2 start;
    private Vector2 end;
    private final float width = 0.05f;

    public Laser(Vector2 start, Vector2 end) {
        this.start = start;
        this.end = end;
    }

    public Vector2 getStartPosition() {
        return start;
    }

    public Vector2 getEndPosition() {
        return end;
    }

    public Vector2 setEndPosition(Vector2 end) {
        this.end = end;
        return end;
    }

    public float getWidth() {
        return width;
    }
}
