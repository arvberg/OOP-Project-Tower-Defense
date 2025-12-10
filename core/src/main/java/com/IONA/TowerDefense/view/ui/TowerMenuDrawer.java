package com.IONA.TowerDefense.view.ui;

import com.IONA.TowerDefense.model.ui.towerui.sideMenu.TowerMenu;
import com.IONA.TowerDefense.model.ui.towerui.sideMenu.TowerMenuItem;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class TowerMenuDrawer {

    public static void drawTowerMenu(TowerMenu towerMenu, Batch batch){
        batch.draw(towerMenu.texture, towerMenu.getMenuPosition().x, towerMenu.getMenuPosition().y,
                   towerMenu.getWidth(), towerMenu.getHeight() );
    }
}
