package com.IONA.TowerDefense.model.models;


import com.IONA.TowerDefense.model.input.GameAction;
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
            case PLAY -> model.startGame();
            case RESTART -> model.restartGame();
            case SPEED_TOGGLE -> model.toggleSpeed();
            case SELL_TOWER -> model.sellTower(model.getSelectedTower());
            case EXIT -> model.exitGame();
            case TOGGLE_TARGETING_STRATEGY -> model.toggleTargetingStrategy();
            case PAUSE_TOGGLE -> model.togglePause();
            case BUY_TOWER -> model.buyTower(
                ((TowerMenuItem) sourceButton).getTowerType()
            );
            case UPGRADE_TOWER -> {
                if (model.isTowerSelected()) {
                    UpgradeMenuItem item = (UpgradeMenuItem) sourceButton;
                    model.upgradeTower(model.getSelectedTower(), item.consumeUpgrade());
                }
            }
            case CANCEL_TOWER -> model.cancelTowerBuy();
        }
    }
}
