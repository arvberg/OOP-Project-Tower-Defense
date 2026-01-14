package com.IONA.TowerDefense.view.ui.buttons;

import com.IONA.TowerDefense.controller.buttonui.CancelButton;
import com.IONA.TowerDefense.controller.buttonui.PlayButton;
import com.IONA.TowerDefense.view.Assets;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

public final class CancelButtonDrawer implements DrawableButton {

    private final CancelButton button;
    private Vector2 p;
    private final float dimensionX;
    private final float dimensionY;

    // STATIC TEXTURE shared by all instances
    private static final Texture TEXTURE = new Texture(Assets.BUTTON_PLAYBUTTON);

    public CancelButtonDrawer(CancelButton button) {
        this.button = button;
        this.dimensionX = button.getWidth();
        this.dimensionY = button.getHeight();
    }

    @Override
    public void draw(SpriteBatch batch, ShapeRenderer shapeRenderer, float delta) {
       return;
    }

    public static void disposeStatic() {
        if (TEXTURE != null) TEXTURE.dispose();
    }
}
