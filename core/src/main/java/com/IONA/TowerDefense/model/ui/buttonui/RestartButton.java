package com.IONA.TowerDefense.model.ui.buttonui;

import com.IONA.TowerDefense.model.input.GameAction;

public class RestartButton extends Button {

    public RestartButton(float x, float y) {
        super(x, y, 1f, 1f, GameAction.RESTART);
    }
}
