package com.IONA.TowerDefense.controller.buttonui;

import com.IONA.TowerDefense.model.input.GameAction;
import com.IONA.TowerDefense.model.ui.Button;

public class TargetingStrategyToggleButton extends Button {
    String defaultTargetingStrategy;
    public TargetingStrategyToggleButton(float x, float y, String defaultTargetingStrategy) {
        super(x, y - 0.3f, 2f, .4f, GameAction.TOGGLE_TARGETING_STRATEGY);
        this.defaultTargetingStrategy = defaultTargetingStrategy;
    }
    public String getDefaultTargetingStrategy(){return defaultTargetingStrategy;}
}
