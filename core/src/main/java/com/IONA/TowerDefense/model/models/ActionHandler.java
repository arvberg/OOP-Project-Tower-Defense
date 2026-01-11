package com.IONA.TowerDefense.model.models;


import com.IONA.TowerDefense.model.input.GameAction;
import com.IONA.TowerDefense.model.ui.Button;
import com.IONA.TowerDefense.model.ui.towerui.sideMenu.TowerMenuItem;
import com.IONA.TowerDefense.model.ui.towerui.sideMenu.UpgradeMenuItem;

/**
 * Handles UI actions triggered by buttons in the game.
 * <p>
 * This class interprets {@link GameAction} enums and executes the corresponding
 * method on the {@link GameModel}. It acts as a bridge between user input
 * and game logic.
 */
public class ActionHandler {

    private final GameModel model;

    public ActionHandler(GameModel model) {
        this.model = model;
    }
    /**
     * Handles a user action triggered by a button.
     * <p>
     * Executes the corresponding game logic depending on the {@link GameAction}.
     * For example, starting the game, buying or selling towers, toggling speed,
     * or pausing the game.
     *
     * @param action the {@link GameAction} to handle
     * @param sourceButton the {@link Button} that triggered the action, can be cast to specific types like {@link TowerMenuItem} or {@link UpgradeMenuItem}
     */
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
                    model.upgradeTower(model.getSelectedTower(), item.getUpgrade(), item);

                }
            }
            case CANCEL_TOWER -> model.cancelTowerBuy();
        }
    }
}
