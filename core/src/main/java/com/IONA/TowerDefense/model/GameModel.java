package com.IONA.TowerDefense.model;

import java.util.List;

// Main model class to for communication with controller
public class GameModel {

    private List<Unit> units;
    private Path path;
    private int money;
    private int health;
    private boolean towerSelected = false;

    // Left-mouse click
    public void onLeftClick(float x, float y) {
        if (towerSelected) {
            // place tower
        }
        else {
            // select tower
        }
    }

    // Right-mouse click
    public void onRightClick(float x, float y) {
        // do something
    }

    // While dragging mouse
    public void onMouseDrag(float x, float y) {
        // do something
    }

    public Tower[] getTowers() {
    }

    public Object getLives() {
    }

    public Object getScore() {
    }
}
