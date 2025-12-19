package com.IONA.TowerDefense;

import com.badlogic.gdx.math.Vector2;
/**
 * Utility class for common vector operations.
 * <p>
 * Provides methods for calculating distance, direction, and angles between 2D vectors.
 * All methods are static and stateless.
 */
public final class VectorUtils {

    public static float distance(Vector2 a, Vector2 b) {
        float deltaX = a.x - b.x;
        float deltaY = a.y - b.y;
        return (float) Math.sqrt(deltaX * deltaX + deltaY * deltaY);
    }

    public static Vector2 direction(Vector2 from, Vector2 to) {
        return new Vector2(to.x - from.x, to.y - from.y).nor();
    }

    public static float angleFromDirection(Vector2 direction) {
        float dx = direction.x;
        float dy = direction.y;

        float radians = (float) Math.atan2(dy, dx);
        float degrees = (float) Math.toDegrees(radians);

        if (degrees < 0) {
            degrees += 360f;
        }

        return degrees;
    }
}
