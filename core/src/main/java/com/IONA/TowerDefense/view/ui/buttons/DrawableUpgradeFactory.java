package com.IONA.TowerDefense.view.ui.buttons;

import com.IONA.TowerDefense.model.ui.buttonui.*;
import com.IONA.TowerDefense.model.ui.towerui.sideMenu.*;
import com.IONA.TowerDefense.model.upgrades.RangeUpgrade;
/**
 * Factory class for creating drawable upgrade buttons.
 * <p>
 * Converts an UpgradeMenuItem into the appropriate DrawableButton for the UI
 * based on the type of upgrade (e.g., Range, Fire Rate, Max). Throws an exception
 * if the upgrade type is not recognized.
 */
public class DrawableUpgradeFactory {

    private DrawableUpgradeFactory() {
    }

    public static DrawableButton create(UpgradeMenuItem item) {
        return switch (item.getNextUpgrade().getName()) {
            case "RangeUpgrade" -> new RangeUpgradeDrawer(item);
            case "FireRateUpgrade" -> new FireRateUpgradeDrawer(item);
            case "MaxUpgrade" -> new UpgradeMenuItemButtonDrawer(item);
            case "DamageUpgrade" -> new DamageUpgradeDrawer(item);
            default -> throw new IllegalStateException("Unexpected value: " + item);
        };
    }
}
