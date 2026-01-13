package com.IONA.TowerDefense.model.states;

import com.IONA.TowerDefense.model.models.GameModel;
import com.IONA.TowerDefense.model.units.enemies.Enemy;

/**
 * Represents the running (active) state of the game.
 * <p>
 * In this state, all game logic is active: enemies move along the path,
 * towers attack, projectiles update, and the core can be damaged.
 * Player interactions such as placing towers, upgrading, and collecting
 * rewards are handled. The state also checks for wave completion and
 * transitions to the start or game over states when appropriate.
 */
public class RunningState implements GameState {
    private GameModel model;

    public RunningState(GameModel model) {
        this.model = model;
    }

    @Override
    public void enter() {
        model.getPlayButton().setVisible(false);
        model.getSpeedUpButton().setVisible(true);
    }

    @Override
    public void update(float delta) {
        // Uppdatera spelets logik
        model.updateEnemies(delta);
        model.coreDamaged();
        model.getAttackHandler().update(delta);
        model.getTowerMenu().update(delta);
        model.getInfoMenu().update(delta);

        // Check if the wave is cleared
        if (model.getGenerator().WaveCleared()) {
            model.getGenerator().WaveReward();
            model.setState(model.getStartState());
        }

        // Set state to game over state
        if (model.getResourceHandler().getLives() <= 0) {
            model.setState(model.getGameOverState());
        }
    }


    @Override
    public void toggle() {
        model.setState(model.getPausedState());
    }

    @Override
    public void enemyDeath(Enemy enemy) {
        // Bara hantera döda fiender om vi är i running state
        int moneyGained = enemy.getMoney();
        model.getResourceHandler().gainMoney(moneyGained);
        model.getResourceHandler().updateMoneyResource();
    }


    @Override
    public void exit() {
        // inget just nu
    }
}
