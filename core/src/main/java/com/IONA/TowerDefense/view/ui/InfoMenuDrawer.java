package com.IONA.TowerDefense.view.ui;

import com.IONA.TowerDefense.model.ui.towerui.sideMenu.InfoMenu;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;

public class InfoMenuDrawer {

    private static final Texture TEXTURE = new Texture("Sidebar.png");

    public static void drawInfoMenu(InfoMenu infoMenu, Batch batch){
        if (infoMenu.isHovered()) {
            batch.draw(TEXTURE, infoMenu.getMenuPosition().x, infoMenu.getMenuPosition().y,
                infoMenu.getWidth(), infoMenu.getHeight());
        }
    }

    public static void disposeStatic() {
        TEXTURE.dispose();
    }

}
