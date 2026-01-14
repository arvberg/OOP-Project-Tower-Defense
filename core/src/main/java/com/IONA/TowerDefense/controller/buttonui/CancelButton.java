package com.IONA.TowerDefense.controller.buttonui;

import com.IONA.TowerDefense.model.input.GameAction;
import com.IONA.TowerDefense.model.ui.Button;

public class CancelButton extends Button {

    public CancelButton(float x, float y, float width, float height) {
        super(x, y, width, height, GameAction.CANCEL_TOWER);
    }

}
