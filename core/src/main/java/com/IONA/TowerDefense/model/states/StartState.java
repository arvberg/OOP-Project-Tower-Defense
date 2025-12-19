package com.IONA.TowerDefense.model.states;

import com.IONA.TowerDefense.model.models.GameModel;

/**
 * Represents the initial state of the game before any waves have started.
 * <p>
 * In this state, the game is idle and waiting for the player to start the game.
 * Only the Play button is visible, and no game logic such as enemy movement or
 * tower attacks is processed.
 */
public class StartState implements GameState {

    private GameModel model;

    public StartState(GameModel model) {
        this.model = model;
    }

    @Override
    public void enter() {
        model.getPlayButton().setVisible(true);
        model.getSpeedUpButton().setVisible(false);
    }

    @Override
    public void update(float delta) {
        // väntar på Play
    }

    @Override
    public void exit() {
    }
}
