package com.IONA.TowerDefense.view.ui.buttons;

import com.IONA.TowerDefense.model.ui.towerui.sideMenu.TowerMenuItem;
import com.IONA.TowerDefense.model.ui.towerui.sideMenu.UpgradeMenuItem;
import com.IONA.TowerDefense.view.units.towers.TowerBasicDrawer;
import com.IONA.TowerDefense.view.units.towers.TowerPulseDrawer;

public class DrawableTowerIconFactory {

    private DrawableTowerIconFactory(){}

    public static DrawableButton create(TowerMenuItem item){
        return switch (item.getName()){
            case "TowerBasic" -> new TowerBasicIconDrawer(item);
            case "TowerPulse" -> new TowerPulseIconDrawer(item);
            default -> throw new IllegalStateException("Unexpected value: " + item);
        };
    }
}
