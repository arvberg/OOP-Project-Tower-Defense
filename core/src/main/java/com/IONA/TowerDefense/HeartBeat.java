package com.IONA.TowerDefense;
/**
 * HeartBeat keeps track of the game's global delta time and speed multiplier.
 * - `delta` is the time elapsed since the last frame (used for frame-independent updates).
 * - `speedMultiplier` allows toggling between normal and double speed for the game.
 */
public class HeartBeat {
    public static float delta;
    private static float speedMultiplier = 1f;

    public static void toggleSpeed() {
        speedMultiplier = (speedMultiplier == 1f) ? 2f : 1f;
    }

    public static float getSpeedMultiplier() {
        return speedMultiplier;
    }
}
