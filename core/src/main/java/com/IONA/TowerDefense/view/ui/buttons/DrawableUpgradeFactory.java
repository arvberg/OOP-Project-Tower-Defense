package com.IONA.TowerDefense.view.ui.buttons;

import com.IONA.TowerDefense.model.ui.buttonui.*;
import com.IONA.TowerDefense.model.ui.towerui.sideMenu.*;
import com.IONA.TowerDefense.model.upgrades.RangeUpgrade;

public class DrawableUpgradeFactory {

    private DrawableUpgradeFactory() {
    }

    public static DrawableButton create(UpgradeMenuItem item) {
        return switch (item.getNextUpgrade().getName()) {
            case "RangeUpgrade" -> new RangeUpgradeDrawer(item);
            case "FireRateUpgrade" -> new FireRateUpgradeDrawer(item);
            case "MaxUpgrade" -> new UpgradeMenuItemButtonDrawer(item);
            default -> throw new IllegalStateException("Unexpected value: " + item);
        };
    }
}
