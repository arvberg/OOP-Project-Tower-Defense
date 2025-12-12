package com.IONA.TowerDefense.view.ui;

import com.IONA.TowerDefense.model.ui.towerui.sideMenu.UpgradeMenu;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;

public class UpgradeMenuDrawer {

    private static final Texture TEXTURE = new Texture("Sidebar.png");

    public static void drawUpgradeMenu(UpgradeMenu upgradeMenu, Batch batch){
        if (upgradeMenu.towerIsClicked()) {
            batch.draw(TEXTURE, upgradeMenu.getMenuPosition().x, upgradeMenu.getMenuPosition().y,
                upgradeMenu.getWidth(), upgradeMenu.getHeight());
        }
    }

    public static void disposeStatic() {
        TEXTURE.dispose();
    }

}
