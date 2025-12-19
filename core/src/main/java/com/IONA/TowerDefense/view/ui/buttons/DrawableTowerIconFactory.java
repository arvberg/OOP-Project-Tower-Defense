package com.IONA.TowerDefense.view.ui.buttons;

import com.IONA.TowerDefense.model.ui.towerui.sideMenu.TowerMenuItem;
import com.IONA.TowerDefense.model.ui.towerui.sideMenu.UpgradeMenuItem;
import com.IONA.TowerDefense.view.units.towers.TowerBasicDrawer;
import com.IONA.TowerDefense.view.units.towers.TowerPulseDrawer;
/**
 * Factory class for creating drawable tower icon buttons.
 * <p>
 * Converts a TowerMenuItem into the corresponding DrawableButton representation
 * for the UI. Each tower type has its own icon drawer. Throws an exception
 * if an unknown tower type is provided.
 */
public class DrawableTowerIconFactory {

    private DrawableTowerIconFactory() {
    }

    public static DrawableButton create(TowerMenuItem item) {
        return switch (item.getName()) {
            case "TowerBasic" -> new TowerBasicIconDrawer(item);
            case "TowerPulse" -> new TowerPulseIconDrawer(item);
            case "TowerMissile" -> new TowerMissileIconDrawer(item);
            default -> throw new IllegalStateException("Unexpected value: " + item);
        };
    }
}
