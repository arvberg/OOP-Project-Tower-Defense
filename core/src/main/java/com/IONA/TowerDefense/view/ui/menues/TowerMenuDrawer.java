package com.IONA.TowerDefense.view.ui.menues;

import com.IONA.TowerDefense.model.ui.towerui.sideMenu.TowerMenu;
import com.IONA.TowerDefense.view.Assets;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import java.awt.*;

public final class TowerMenuDrawer implements DrawableMenu {

    private static final Texture TEXTURE = new Texture(Assets.MENU_TOWERMENU);
    private static final Texture TEXTURE_CANCEL = new Texture(Assets.MENU_TOWERMENU_CANCEL);

    private final TowerMenu towerMenu;

    public TowerMenuDrawer(TowerMenu towerMenu){
        this.towerMenu = towerMenu;
    }

    @Override
    public void draw(SpriteBatch batch, ShapeRenderer shapeRenderer, float delta){
        return;
        /*
        batch.draw(TEXTURE, towerMenu.getMenuPosition().x, towerMenu.getMenuPosition().y,
            towerMenu.getWidth(), towerMenu.getHeight() );

         */
    }

    @Override
    public void drawCancelTower(SpriteBatch batch){

        batch.draw(TEXTURE_CANCEL, towerMenu.getMenuPosition().x, towerMenu.getMenuPosition().y,
            towerMenu.getWidth(), towerMenu.getHeight() );
    }

    public static void disposeStatic() {
        TEXTURE.dispose();
    }

}
