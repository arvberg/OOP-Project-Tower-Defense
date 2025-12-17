package com.IONA.TowerDefense.view.ui.menues;

import com.IONA.TowerDefense.model.ui.towerui.sideMenu.InfoMenu;
import com.IONA.TowerDefense.view.Assets;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public final class InfoMenuDrawer implements DrawableMenu {

    private static final Texture TEXTURE = new Texture(Assets.MENU_INFOMENU_TOWERBASIC);

    private final InfoMenu infoMenu;

    public InfoMenuDrawer(InfoMenu infoMenu){
        this.infoMenu = infoMenu;
    }

    @Override
    public void draw(SpriteBatch batch, ShapeRenderer shapeRenderer, float delta){
        if (infoMenu.isHovered()) {
            batch.draw(TEXTURE, infoMenu.getMenuPosition().x, infoMenu.getMenuPosition().y,
                infoMenu.getWidth(), infoMenu.getHeight());
        }
    }

    public static void disposeStatic() {
        TEXTURE.dispose();
    }

}
