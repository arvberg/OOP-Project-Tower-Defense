package com.IONA.TowerDefense.view.ui.menues;

import com.IONA.TowerDefense.model.ui.Menu;
import com.IONA.TowerDefense.model.ui.towerui.sideMenu.InfoMenu;
import com.IONA.TowerDefense.model.ui.towerui.sideMenu.TowerMenu;
import com.IONA.TowerDefense.model.ui.towerui.sideMenu.UpgradeMenu;

public final class DrawableUpgradeMenuFactory {

    private DrawableUpgradeMenuFactory(){}

    public static DrawableMenu create(UpgradeMenu menu){
        return switch (menu.getBelongsTo()){
            case "TowerBasic" -> new UpgradeMenuTowerBasicDrawer(menu);
            case "TowerPulse" -> new UpgradeMenuTowerPulseDrawer(menu);
            case "TowerMissile" -> new UpgradeMenuTowerMissileDrawer(menu);
            default -> throw new IllegalStateException("Unexpected value: " + menu);
        };
    }
}
