package com.IONA.TowerDefense.controller.buttonui;

import com.IONA.TowerDefense.model.input.GameAction;

public class ExitButton extends Button {

    public ExitButton(float x, float y) {
        super(x, y, 1f, 1f, GameAction.EXIT);
    }
}
