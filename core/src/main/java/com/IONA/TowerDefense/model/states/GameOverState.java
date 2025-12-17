package com.IONA.TowerDefense.model.states;

import com.IONA.TowerDefense.model.models.GameModel;
import com.badlogic.gdx.Game;

public class GameOverState implements GameState {

    private GameModel model;

    public GameOverState(GameModel model) {
        this.model = model;
    }

    @Override
    public void enter() {
        model.getRestartButton().setVisible(true);
        model.getExitButton().setVisible(true);
    }

    @Override
    public void update(float delta) {
        // inget gameplay
    }

    @Override
    public void exit() {
    }


    @Override
    public void restartGame() {
        // Reset all game data
        model.getEnemyHandler().removeAllEnemies();
        model.getTowerHandler().removeAllTowers();
        model.getAttackHandler().removeAllProjectiles();
        model.getResourceHandler().resetResources();
        model.getGenerator().resetWaves();

        // Byt till START state
        model.setState(model.getStartState());
        model.updateButtonLayout();
    }
}
