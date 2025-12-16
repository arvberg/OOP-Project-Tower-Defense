package com.IONA.TowerDefense.model.ui.buttonui;

import com.IONA.TowerDefense.model.input.GameAction;

public class TargetingStrategyToggleButton extends Button {

    public TargetingStrategyToggleButton(float x, float y) {
        super(x, y, 1, 1, GameAction.TOGGLE_TARGETING_STRATEGY);
    }
}
