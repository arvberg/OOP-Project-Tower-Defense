package com.IONA.TowerDefense.controller.buttonui;

import com.IONA.TowerDefense.model.input.GameAction;
import com.IONA.TowerDefense.model.ui.Button;
import com.IONA.TowerDefense.model.ui.towerui.sideMenu.UpgradeMenu;

public class SellButton extends Button {

    public SellButton(float x, float y, UpgradeMenu menu) {

        //WIDTH:2.4HEIGHT:0.4
        super(x, y - 0.4f, 2f, .4f, GameAction.SELL_TOWER);
    }
}
