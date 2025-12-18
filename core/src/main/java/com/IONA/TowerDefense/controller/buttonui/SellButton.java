package com.IONA.TowerDefense.controller.buttonui;

import com.IONA.TowerDefense.model.input.GameAction;

public class SellButton extends Button {

    public SellButton(float x, float y) {
        super(x, y, 1f, 1f, GameAction.SELL_TOWER);
    }
}
