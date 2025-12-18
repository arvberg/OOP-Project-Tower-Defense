package com.IONA.TowerDefense.controller.buttonui;

import com.IONA.TowerDefense.model.input.GameAction;

public class SpeedUpButton extends Button {

    public SpeedUpButton(float x, float y) {
        super(x, y, 0.8f, 0.8f, GameAction.SPEED_TOGGLE);
    }
}
