package com.IONA.TowerDefense.controller.buttonui;

import com.IONA.TowerDefense.model.input.GameAction;
import com.IONA.TowerDefense.model.ui.Button;

public class RestartButton extends Button {

    public RestartButton(float x, float y) {
        super(x, y, 1f, 1f, GameAction.RESTART);
    }
}
