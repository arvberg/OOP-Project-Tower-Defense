package com.IONA.TowerDefense.model.states;

import com.IONA.TowerDefense.model.models.GameModel;
import com.IONA.TowerDefense.model.ui.Button;
import com.badlogic.gdx.math.Vector2;

public class PauseState implements GameState {

    private GameModel model;

    public PauseState(GameModel model) {
        this.model = model;
    }

    public void enter(GameModel model) {
        model.getPauseButton().setVisible(false);
        model.getPlayButton().setVisible(true); // Resume
        model.getSpeedUpButton().setVisible(false);
    }

    @Override
    public void handleInput(GameModel model, Vector2 pos) {
        // Endast UI-knappar kan klickas, ingen tornplacering
        for (Button b : model.getInGameButtons()) {
            if (b.isClicked(pos)) {
                model.handleAction(b.getAction(), b);
                return;
            }
        }
    }

    @Override
    public void update(float delta) {
        // gör ingenting
    }

    @Override
    public void toggle() {
        model.setState(model.getRunningState());
    }

    @Override
    public void exit() {
    }
}
