package com.IONA.TowerDefense.view.ui.menues;

import com.IONA.TowerDefense.model.ui.Menu;
import com.IONA.TowerDefense.model.ui.towerui.sideMenu.InfoMenu;
import com.IONA.TowerDefense.model.ui.towerui.sideMenu.TowerMenu;
import com.IONA.TowerDefense.model.ui.towerui.sideMenu.UpgradeMenu;
import com.IONA.TowerDefense.model.units.enemies.Enemy;
import com.IONA.TowerDefense.model.units.enemies.EnemyBasic;
import com.IONA.TowerDefense.view.units.enemies.DrawableEnemy;
import com.IONA.TowerDefense.view.units.enemies.EnemyBasicDrawer;

public final class DrawableMenuFactory {

    private DrawableMenuFactory(){}

    public static DrawableMenu create(Menu menu){
        return switch (menu){
            case TowerMenu m -> new TowerMenuDrawer(m);
            case UpgradeMenu m -> new UpgradeMenuDrawer(m);
            case InfoMenu m -> new InfoMenuDrawer(m);
            default -> throw new IllegalStateException("Unexpected value: " + menu);
        };
    }
}
