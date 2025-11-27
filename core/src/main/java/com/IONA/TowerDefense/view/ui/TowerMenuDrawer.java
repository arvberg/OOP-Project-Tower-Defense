package com.IONA.TowerDefense.view.ui;

import com.IONA.TowerDefense.model.ui.towerui.TowerMenu;
import com.badlogic.gdx.graphics.g2d.Batch;

public class TowerMenuDrawer {

    public static void drawTowerMenu(TowerMenu towerMenu, Batch batch){
        batch.draw(towerMenu.texture, towerMenu.getMenuPosition().x, towerMenu.getMenuPosition().y,
                   towerMenu.getWidth(), towerMenu.getHeight() );
    }
}
