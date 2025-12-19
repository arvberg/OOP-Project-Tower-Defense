package com.IONA.TowerDefense.view.ui.menues;

import com.IONA.TowerDefense.model.ui.Menu;
import com.IONA.TowerDefense.model.ui.towerui.sideMenu.InfoMenu;
import com.IONA.TowerDefense.model.ui.towerui.sideMenu.TowerMenu;
import com.IONA.TowerDefense.model.ui.towerui.sideMenu.UpgradeMenu;

public final class DrawableMenuFactory {

    private DrawableMenuFactory(){}

    public static DrawableMenu create(Menu menu){
        return switch (menu){
            case TowerMenu m -> new TowerMenuDrawer(m);
            case UpgradeMenu m-> DrawableUpgradeMenuFactory.create(m);
            case InfoMenu m -> new InfoMenuDrawer(m);
            default -> throw new IllegalStateException("Unexpected value: " + menu);
        };
    }
}
