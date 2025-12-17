package com.IONA.TowerDefense.model.ui.towerui.sideMenu;

import com.IONA.TowerDefense.model.input.GameAction;
import com.IONA.TowerDefense.model.ui.buttonui.Button;

public class TowerMenuItem extends Button {

    private final String towerType;

    public TowerMenuItem(float x, float y, String towerType) {
        super(x - .9f / 2, y - .9f / 2, .9f, 1.1f, GameAction.BUY_TOWER);
        this.towerType = towerType;
    }

    public String getTowerType() {
        return towerType;
    }


    public String getName() {
        return towerType;
    }
}
