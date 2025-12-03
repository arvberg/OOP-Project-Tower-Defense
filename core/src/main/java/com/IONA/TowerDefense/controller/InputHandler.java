package com.IONA.TowerDefense.controller;

import com.IONA.TowerDefense.model.GameState;
import com.IONA.TowerDefense.model.models.GameModel;
import com.IONA.TowerDefense.model.ui.buttonui.Button;
import com.IONA.TowerDefense.model.ui.buttonui.PauseButton;
import com.IONA.TowerDefense.model.ui.buttonui.PlayButton;
import com.IONA.TowerDefense.model.ui.buttonui.RestartButton;
import com.IONA.TowerDefense.model.ui.buttonui.SpeedUpButton;
import com.IONA.TowerDefense.model.ui.towerui.sideMenu.*;
import com.IONA.TowerDefense.model.units.towers.Tower;
import com.badlogic.gdx.math.Vector2;

import java.util.List;

public class InputHandler {

    private final PlayButton playButton;
    private final RestartButton restartButton;
    private final SpeedUpButton speedUpButton;
    private final PauseButton pauseButton;
    private final TowerMenuToggleButton towerMenuToggleButton;
    private final UpgradeMenuToggleButton upgradeMenuToggleButton;
    private final SideMenuToggleButton sideMenuToggleButton;
    private final List<TowerMenuItem> towerMenuItems;
    private final List<UpgradeMenuItem> upgradeMenuItems;
    private final List<Tower> towers;

    private GameModel model;

    public InputHandler (GameModel model) {

        this.playButton = model.getPlayButton();
        this.restartButton = model.getRestartButton();
        this.speedUpButton = model.getSpeedUpButton();
        this.pauseButton = model.getPauseButton();
        this.towerMenuToggleButton = model.getTowerMenuToggleButton();
        this.towerMenuItems = model.getTowerMenuItems();
        this.upgradeMenuToggleButton = model.getUpgradeMenuToggleButton();
        this.upgradeMenuItems = model.getUpgradeMenuItems();
        this.sideMenuToggleButton = model.getSideMenuToggleButton();
        this.model = model;
        this.towers = model.getTowers();
    }


    public void checkInput(Vector2 pos){

        pauseButton.isClicked(pos);

        if (model.getGameState() == GameState.GAME_OVER) {
            restartButton.isClicked(pos);
            return;
        }

        if (model.getGameState() == GameState.PAUSED) {
            return;
        }

        // Först UI-kontroller
        towerMenuToggleButton.isClicked(pos);
        upgradeMenuToggleButton.isClicked(pos);
        sideMenuToggleButton.isClicked(pos);

        playButton.isClicked(pos);
        for (TowerMenuItem t : towerMenuItems) {
            t.isClicked(pos);
        }
        for (UpgradeMenuItem u : upgradeMenuItems){
            u.isClicked(pos);
        }

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
            model.selectTower(pos);
            if (model.isTowerSelected()) {
                Tower selected = model.getSelectedTower();
            }
        }
    }

    // Handle input for mouse hovering
    public void updateMouse(Vector2 worldMousePos) {
        model.updateTowerFollowingMouse(worldMousePos);
    }

    private boolean clickedOnGameArea(Vector2 pos) {
        for (Button b : model.getInGameButtons()) {
            if (b.contains(pos.x, pos.y)) {
                return false;
            }
        }
        return true;
    }
}
