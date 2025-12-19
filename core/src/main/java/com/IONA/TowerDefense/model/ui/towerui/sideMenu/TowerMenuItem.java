package com.IONA.TowerDefense.model.ui.towerui.sideMenu;

import com.IONA.TowerDefense.model.input.GameAction;
import com.IONA.TowerDefense.model.ui.buttonui.Button;

public class TowerMenuItem extends Button {

    private final String towerType;

    public TowerMenuItem(float x, float y, String towerType) {
        super(x - 1.2f / 2, y - 1.2f / 2, 1.2f, 1.2f, GameAction.BUY_TOWER);
        this.towerType = towerType;
    }

    public String getTowerType() {
        return towerType;
    }


    public String getName() {
        return towerType;
    }
}
