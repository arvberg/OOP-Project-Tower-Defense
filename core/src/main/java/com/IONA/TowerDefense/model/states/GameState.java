package com.IONA.TowerDefense.model.states;

import com.IONA.TowerDefense.model.models.GameModel;
import com.IONA.TowerDefense.model.ui.Menu;
import com.IONA.TowerDefense.model.ui.buttonui.Button;
import com.IONA.TowerDefense.model.ui.towerui.sideMenu.TowerMenuItem;
import com.IONA.TowerDefense.model.units.enemies.Enemy;
import com.IONA.TowerDefense.model.units.towers.Tower;
import com.badlogic.gdx.math.Vector2;

/**
 * Represents a state in the Tower Defense game.
 * <p>
 * GameState defines the behavior of the game during different phases,
 * such as start, running, paused, or game over. Each concrete state
 * can override the default methods to handle entering the state,
 * updating game logic, processing input, handling hover events,
 * toggling pause, responding to enemy deaths, restarting, or exiting the game.
 * <p>
 * Default implementations provide basic input handling for UI buttons,
 * tower selection, and tower placement, which can be reused or overridden
 * by specific states.
 */
public interface GameState {

    default void enter() {
    }

    default void update(float delta) {
    }

    // default inputhantering.
    // Gäller för Start och Running. Kan flyttas till separata state-klasser om vi får fler states
    default void handleInput(GameModel model, Vector2 pos) {
        // Standardinput för UI, torn och upgrade menus
        for (Button button : model.getInGameButtons()) {
            if (button.isClicked(pos)) {
                model.handleAction(button.getAction(), button);
                return;
            }
        }

        for (TowerMenuItem item : model.getTowerMenuItems()) {
            if (item.isClicked(pos)) {
                model.handleAction(item.getAction(), item);
                return;
            }
        }

        for (Button item : model.getUpgradeMenuItems()) {
            if (item.isClicked(pos)) {
                model.handleAction(item.getAction(), item);
                return;
            }
        }

        if (model.isBuyingState() && clickedOnGameArea(model, pos)) {
            model.placeTower(pos);
            return;
        }

        if (clickedOnGameArea(model, pos)) {
            Tower clicked = model.getTowerAt(pos);
            if (clicked != null) {
                model.selectTower(pos);
            } else {
                model.deselectTower();
            }
        }
    }

    default void handleHover(GameModel model, Vector2 pos) {
        model.updateTowerFollowingMouse(pos);

    }

    default void toggle() {
    }

    default void enemyDeath(Enemy enemy) {
    }

    default void restartGame() {
    }

    default void exit() {
    }


    private static boolean clickedOnGameArea(GameModel model, Vector2 pos) {
        for (Button b : model.getInGameButtons()) {
            if (b.contains(pos.x, pos.y)) return false;
        }
        for (Menu m : model.getMenus()) {
            if (m.contains(pos.x, pos.y)) return false;
        }
        return true;
    }

}
