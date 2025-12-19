package com.IONA.TowerDefense.model.states;

import com.IONA.TowerDefense.model.models.GameModel;

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
    public void exit() {}
}
