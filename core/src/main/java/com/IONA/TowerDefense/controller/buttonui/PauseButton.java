package com.IONA.TowerDefense.controller.buttonui;

import com.IONA.TowerDefense.model.input.GameAction;
import com.IONA.TowerDefense.model.ui.Button;

public class PauseButton extends Button {

    public PauseButton(float x, float y) {
        super(0.1f, 9f - 0.9f, 0.8f, 0.8f, GameAction.PAUSE_TOGGLE);
    }
}
