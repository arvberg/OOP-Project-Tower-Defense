package com.IONA.TowerDefense.model.ui.buttonui;

import com.IONA.TowerDefense.model.input.GameAction;

public class CancelButton extends Button {

    public CancelButton(float x, float y) {
        super(x, y, 1f, 1f, GameAction.RESTART);
    }

}
