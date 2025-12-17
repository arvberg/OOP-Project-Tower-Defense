package com.IONA.TowerDefense.view.ui.buttons;

import com.IONA.TowerDefense.model.ui.towerui.sideMenu.UpgradeMenuItem;
import com.IONA.TowerDefense.view.Assets;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

public final class FireRateUpgradeDrawer implements DrawableButton {

    private Vector2 p;
    private final UpgradeMenuItem item;
    private final float dimensionX;
    private final float dimensionY;

    // STATIC TEXTURE shared by all instances
    private static final Texture TEXTURE = new Texture(Assets.BUTTON_PLAYBUTTON);

    public FireRateUpgradeDrawer(UpgradeMenuItem item) {
        this.item = item;
        this.dimensionX = item.getWidth();
        this.dimensionY = item.getHeight();
    }

    @Override
    public void draw(SpriteBatch batch, ShapeRenderer shapeRenderer, float delta) {
        p = item.getButtonPosition();
        batch.draw(TEXTURE, p.x, p.y, dimensionX, dimensionY);
    }

    public static void disposeStatic() {
        if (TEXTURE != null) TEXTURE.dispose();
    }
}
