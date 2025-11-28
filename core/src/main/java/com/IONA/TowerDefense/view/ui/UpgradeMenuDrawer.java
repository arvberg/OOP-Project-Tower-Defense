package com.IONA.TowerDefense.view.ui;

import com.IONA.TowerDefense.model.ui.towerui.sideMenu.UpgradeMenu;
import com.badlogic.gdx.graphics.g2d.Batch;

public class UpgradeMenuDrawer {
    public static void drawUpgradeMenu(UpgradeMenu upgradeMenu, Batch batch){
        batch.draw(upgradeMenu.texture, upgradeMenu.getMenuPosition().x, upgradeMenu.getMenuPosition().y,
            upgradeMenu.getWidth(), upgradeMenu.getHeight() );
    }
}
