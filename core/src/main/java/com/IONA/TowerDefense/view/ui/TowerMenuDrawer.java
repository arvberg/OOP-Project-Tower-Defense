package com.IONA.TowerDefense.view.ui;

import com.IONA.TowerDefense.model.ui.towerMenu;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;

public class TowerMenuDrawer {

    public static void drawTowerMenu(towerMenu towerMenu, Batch batch){
        batch.draw(towerMenu.texture, towerMenu.getMenuPosition().x, towerMenu.getMenuPosition().y,
                   towerMenu.width, towerMenu.height );
    }

}
