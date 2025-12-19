package com.IONA.TowerDefense.view.ui.buttons;

import com.IONA.TowerDefense.model.ui.towerui.sideMenu.*;

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
