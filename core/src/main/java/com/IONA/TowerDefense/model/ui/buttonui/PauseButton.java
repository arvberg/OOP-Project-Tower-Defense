package com.IONA.TowerDefense.model.ui.buttonui;

import com.IONA.TowerDefense.model.input.GameAction;

public class PauseButton extends Button {

    public PauseButton(float x, float y) {
        super(x, y, 1, 1, GameAction.PAUSE_TOGGLE);
    }
}
