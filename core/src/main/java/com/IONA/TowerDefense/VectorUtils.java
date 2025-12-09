package com.IONA.TowerDefense;

import com.badlogic.gdx.math.Vector2;

public class VectorUtils {

    public static Vector2 direction(Vector2 from, Vector2 to) {
        Vector2 diff = to.cpy().sub(from);
        return diff.nor();
    }

    public static float distance(Vector2 from, Vector2 to) {
        float dx = to.x - from.x;
        float dy = to.y - from.y;
        return (float) Math.sqrt(dx * dx + dy * dy);
    }
}
