package com.IONA.TowerDefense.view;

import com.IONA.TowerDefense.model.GameModel;

import java.awt.*;

public class GameView {

    private final GameModel model;

    public GameView (GameModel model) {
        this.model = model;
    }

    /* render all towers and update UI
     public void render() {
        for (Tower tower : model.getTowers()) {
            drawTower(tower.getPosition());
        }
        drawUI(model.getScore(), model.getLives());
    } */

    // Draw UI
    private void drawUI(Object score, Object lives) {
    }

    private void drawTower(Point position) {
    }

}
