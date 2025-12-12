package com.IONA.TowerDefense.view.ui.menues;

import com.IONA.TowerDefense.model.ui.towerui.sideMenu.InfoMenu;
import com.IONA.TowerDefense.model.ui.towerui.sideMenu.UpgradeMenu;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public final class UpgradeMenuDrawer implements DrawableMenu {

    private static final Texture TEXTURE = new Texture("Sidebar.png");

    private final UpgradeMenu upgradeMenu;

    public UpgradeMenuDrawer(UpgradeMenu upgradeMenu){
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

