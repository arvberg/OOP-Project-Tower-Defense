package com.IONA.TowerDefense.view.ui.menues;

import com.IONA.TowerDefense.model.ui.Menu;
import com.IONA.TowerDefense.model.ui.towerui.sideMenu.InfoMenu;
import com.IONA.TowerDefense.model.ui.towerui.sideMenu.TowerMenu;
import com.IONA.TowerDefense.model.ui.towerui.sideMenu.UpgradeMenu;
/**
 * Factory class for creating drawable representations of upgrade menus.
 *
 * Converts a given UpgradeMenu into its corresponding DrawableMenu implementation
 * based on the type of tower it belongs to (TowerBasic, TowerPulse, TowerMissile).
 * Throws an exception if the tower type is unknown.
 */
public final class DrawableUpgradeMenuFactory {

    private DrawableUpgradeMenuFactory() {
    }

    public static DrawableMenu create(UpgradeMenu menu) {
        return switch (menu.getBelongsTo()) {
            case "TowerBasic" -> new UpgradeMenuTowerBasicDrawer(menu);
            case "TowerPulse" -> new UpgradeMenuTowerPulseDrawer(menu);
            case "TowerMissile" -> new UpgradeMenuTowerMissileDrawer(menu);
            default -> throw new IllegalStateException("Unexpected value: " + menu);
        };
    }
}
