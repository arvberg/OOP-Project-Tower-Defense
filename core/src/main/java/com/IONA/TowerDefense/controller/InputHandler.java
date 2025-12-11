package com.IONA.TowerDefense.controller;

import com.IONA.TowerDefense.model.GameState;
import com.IONA.TowerDefense.model.models.GameModel;
import com.IONA.TowerDefense.model.ui.buttonui.*;
import com.IONA.TowerDefense.model.ui.towerui.sideMenu.*;
import com.IONA.TowerDefense.model.ui.Menu;
import com.IONA.TowerDefense.model.units.towers.Tower;
import com.badlogic.gdx.math.Vector2;

import java.util.List;

public class InputHandler {

    private final PlayButton playButton;
    private final ExitButton exitButton;
    private final RestartButton restartButton;
    private final SpeedUpButton speedUpButton;
    private final PauseButton pauseButton;
    private final List<TowerMenuItem> towerMenuItems;
    private final List<Tower> towers;

    private GameModel model;

    public InputHandler (GameModel model) {

        this.playButton = model.getPlayButton();
        this.exitButton = model.getExitButton();
        this.restartButton = model.getRestartButton();
        this.speedUpButton = model.getSpeedUpButton();
        this.pauseButton = model.getPauseButton();
        this.towerMenuItems = model.getTowerMenuItems();
        this.model = model;
        this.towers = model.getTowers();
    }


    public void checkInput(Vector2 pos){

        pauseButton.isClicked(pos);

        if (model.getGameState() == GameState.GAME_OVER) {
            restartButton.isClicked(pos);
            exitButton.isClicked(pos);
            return;
        }

        if (model.getGameState() == GameState.PAUSED) {
            return;
        }

        // Först UI-kontroller

        playButton.isClicked(pos);
        for (TowerMenuItem t : towerMenuItems) {
            t.isClicked(pos);
        }

        /*
        for (Button u : upgradeMenuItems){
            u.isClicked(pos);
        }

         */

        speedUpButton.isClicked(pos);


        // Om spelaren har ett torn redo att placera
        if (model.isBuyingState()) {
            if (clickedOnGameArea(pos)) {
                model.placeTower(pos);
            }// Tornet placeras
            return; // Avsluta, vi ska inte välja torn ännu
        }

        // Endast välj torn om man INTE håller på att placera ett nytt
        if (clickedOnGameArea(pos)) {
            Tower clickedTower = model.getTowerAt(pos); // returnerar null om ingen torn på pos

            if (clickedTower != null) {
                model.selectTower(pos);
            } else {
                model.deselectTower();
            }
        }
    }

    // Handle input for mouse hovering
    public void updateMouse(Vector2 worldMousePos) {
        model.updateTowerFollowingMouse(worldMousePos);

        for (TowerMenuItem item : towerMenuItems) {
            item.setHovered(
                item.contains(worldMousePos.x, worldMousePos.y)
            );
        }
    }

    private boolean clickedOnGameArea(Vector2 pos) {
        for (Button b : model.getInGameButtons()) {
            if (b.contains(pos.x, pos.y)) {
                return false;
            }
        }
        for (Menu m : model.getMenus()) {
            if (m.contains(pos.x, pos.y)) {
                return false;
            }
        }

        return true;
    }
}
