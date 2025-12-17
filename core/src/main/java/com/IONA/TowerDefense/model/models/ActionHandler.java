package com.IONA.TowerDefense.model.models;

import com.IONA.TowerDefense.HeartBeat;
import com.IONA.TowerDefense.model.GameStateEnum;
import com.IONA.TowerDefense.model.input.GameAction;
import com.IONA.TowerDefense.model.WaveGenerator;
import com.IONA.TowerDefense.model.ui.buttonui.Button;
import com.IONA.TowerDefense.model.ui.towerui.sideMenu.TowerMenuItem;
import com.IONA.TowerDefense.model.ui.towerui.sideMenu.UpgradeMenuItem;

public class ActionHandler {

    private final GameModel model;

    public ActionHandler(GameModel model) {
        this.model = model;
    }

    public void handleAction(GameAction action, Button sourceButton) {
        switch (action) {
            case PLAY -> startGame();
            case RESTART -> model.restartGame();
            case SPEED_TOGGLE -> toggleSpeed();
            case SELL_TOWER -> sellSelectedTower();
            case EXIT -> exitGame();
            case TOGGLE_TARGETING_STRATEGY -> toggleTargetingStrategy();
            case PAUSE_TOGGLE -> togglePause();
            case BUY_TOWER -> model.buyTower(
                ((TowerMenuItem) sourceButton).getTowerType()
            );
            case UPGRADE_TOWER -> {
                if (model.isTowerSelected()) {
                    UpgradeMenuItem item = (UpgradeMenuItem) sourceButton;
                    model.upgradeTower(model.getSelectedTower(), item.consumeUpgrade());
                }
            }
        }
    }

    private void startGame() {
        WaveGenerator generator = model.getGenerator();

        if (generator.getWaveNr() == 3) {
            generator.setGameDiff(generator.getGameDiff() + 1);
            generator.resetWaves();
        }

        generator.SpawnNextWave();
        model.setState(model.getRunningState());
        model.updateButtonLayout();
    }

    private void toggleSpeed() {
        HeartBeat.toggleSpeed();
    }

    private void togglePause() {
        model.togglePause();
    }


    private void sellSelectedTower() {
        if (model.isTowerSelected()) {
            model.sellTower(model.getSelectedTower());
            model.deselectTower();
        }
    }

    private void toggleTargetingStrategy() {
        if (model.isTowerSelected()) {
            model.getTowerHandler().toggleTargetingStrategy();
        }
    }


    private void exitGame() {
        com.badlogic.gdx.Gdx.app.exit();
    }


}
