package com.IONA.TowerDefense.model.ui.buttonui;

import com.IONA.TowerDefense.model.input.GameAction;

public class PlayButton extends Button {

    public PlayButton(float x, float y) {
        super(x, y, 0.8f, 0.8f, GameAction.PLAY);
    }
}
