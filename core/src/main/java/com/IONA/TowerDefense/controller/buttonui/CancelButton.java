package com.IONA.TowerDefense.controller.buttonui;

import com.IONA.TowerDefense.model.input.GameAction;

public class CancelButton extends Button {

    public CancelButton(float x, float y) {
        super(x, y, 1f, 1f, GameAction.CANCEL_TOWER);
    }

}
