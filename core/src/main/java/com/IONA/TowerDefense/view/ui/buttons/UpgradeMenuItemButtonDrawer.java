package com.IONA.TowerDefense.view.ui.buttons;

import com.IONA.TowerDefense.model.ui.towerui.sideMenu.UpgradeMenuItem;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

public final class UpgradeMenuItemButtonDrawer implements DrawableButton {

    private final UpgradeMenuItem button;
    private Vector2 p;
    private final float dimensionX;
    private final float dimensionY;

    // STATIC TEXTURE
    private static final Texture TEXTURE = new Texture("TowerBasic.png");

    public UpgradeMenuItemButtonDrawer(UpgradeMenuItem button) {
        this.button = button;
        this.dimensionX = button.getWidth();
        this.dimensionY = button.getHeight();
    }

    @Override
    public void draw(SpriteBatch batch, ShapeRenderer shapeRenderer, float delta) {
        p = button.getButtonPosition();
        batch.draw(TEXTURE, p.x, p.y, dimensionX, dimensionY);
    }

    public static void disposeStatic() {
        if (TEXTURE != null) TEXTURE.dispose();
    }
}
