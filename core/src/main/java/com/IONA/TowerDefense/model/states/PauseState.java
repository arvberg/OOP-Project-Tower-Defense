package com.IONA.TowerDefense.model.states;

import com.IONA.TowerDefense.model.models.GameModel;

public class PauseState implements GameState {

    private GameModel model;

    public PauseState(GameModel model) {
        this.model = model;
    }

    @Override
    public void enter() {
        // kanske visa paus-overlay
    }

    @Override
    public void update(float delta) {
        // gör INGENTING
    }

    @Override
    public void toggle() {
        model.setState(model.getRunningState());
    }

    @Override
    public void exit() {}
}
