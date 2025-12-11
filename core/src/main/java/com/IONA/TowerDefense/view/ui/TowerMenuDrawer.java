package com.IONA.TowerDefense.view.ui;

import com.IONA.TowerDefense.model.ui.towerui.sideMenu.TowerMenu;
import com.IONA.TowerDefense.model.ui.towerui.sideMenu.TowerMenuItem;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class TowerMenuDrawer {

    private static final Texture TEXTURE = new Texture("Sidebar.png");

    public static void drawTowerMenu(TowerMenu towerMenu, Batch batch){
        batch.draw(TEXTURE, towerMenu.getMenuPosition().x, towerMenu.getMenuPosition().y,
                   towerMenu.getWidth(), towerMenu.getHeight() );
    }

    public static void disposeStatic() {
        TEXTURE.dispose();
    }

}


