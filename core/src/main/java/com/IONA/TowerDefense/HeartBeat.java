package com.IONA.TowerDefense;

public class HeartBeat {
    public static float delta;
    private static float speedMultiplier = 1f;

    public static void toggleSpeed(){
        speedMultiplier = (speedMultiplier == 1f) ? 2f : 1f;
    }

    public static float getSpeedMultiplier(){
        return speedMultiplier;
    }
}
