package com.IONA.TowerDefense.model.states;

import com.IONA.TowerDefense.model.models.GameModel;
import com.IONA.TowerDefense.model.ui.Button;
import com.badlogic.gdx.math.Vector2;

/**
 * Represents the game state when the player has lost the game.
 * <p>
 * This state handles visibility of relevant buttons (restart, exit),
 * updates enemies and core damage, and manages input specific to the game over screen.
 */
public class GameOverState implements GameState {

    private GameModel model;

    public GameOverState(GameModel model) {
        this.model = model;
    }

    @Override
    public void enter() {
        model.getRestartButton().setVisible(true);
        model.getExitButton().setVisible(true);
        model.getPlayButton().setVisible(false);
        model.getSpeedUpButton().setVisible(false);
    }

    @Override
    public void update(float delta) {
        model.updateEnemies(delta);
        model.coreDamaged();
    }

    @Override
    public void handleInput(GameModel model, Vector2 pos) {
        // Endast knappar i gameOver-buttons kan klickas
        for (Button b : model.getGameOverButtons()) {
            if (b.isClicked(pos)) {
                model.handleAction(b.getAction(), b);
                return;
            }
        }
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
