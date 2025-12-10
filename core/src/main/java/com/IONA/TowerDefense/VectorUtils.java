package com.IONA.TowerDefense;

import com.badlogic.gdx.math.Vector2;

public final class VectorUtils {

    public static float distance(Vector2 a, Vector2 b) {
        float deltaX = a.x - b.x;
        float deltaY = a.y - b.y;
        return (float) Math.sqrt(deltaX * deltaX + deltaY * deltaY);
    }

    public static Vector2 direction(Vector2 from, Vector2 to) {
        return new Vector2(to.x - from.x, to.y - from.y).nor();
    }
}
