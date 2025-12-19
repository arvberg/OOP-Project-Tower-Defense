package com.IONA.TowerDefense.view.ui.menues;

import com.IONA.TowerDefense.model.ui.towerui.sideMenu.UpgradeMenu;
import com.IONA.TowerDefense.view.Assets;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public final class UpgradeMenuTowerMissileDrawer implements DrawableMenu {

    private static final Texture TEXTURE = new Texture(Assets.MENU_UPGRADEMENU_TOWERMISSILE);

    private final UpgradeMenu upgradeMenu;

    public UpgradeMenuTowerMissileDrawer(UpgradeMenu upgradeMenu){
        this.upgradeMenu = upgradeMenu;
    }

    @Override
    public void draw(SpriteBatch batch, ShapeRenderer shapeRenderer, float delta){
        if (upgradeMenu.towerIsClicked()) {
            batch.draw(TEXTURE, upgradeMenu.getMenuPosition().x, upgradeMenu.getMenuPosition().y,
                upgradeMenu.getWidth(), upgradeMenu.getHeight());
        }
    }

    public static void disposeStatic() {
        TEXTURE.dispose();
    }

}

