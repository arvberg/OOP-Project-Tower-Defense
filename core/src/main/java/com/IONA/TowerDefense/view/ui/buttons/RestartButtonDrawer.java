package com.IONA.TowerDefense.view.ui.buttons;

import com.IONA.TowerDefense.model.ui.buttonui.RestartButton;
import com.IONA.TowerDefense.view.Assets;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

public final class RestartButtonDrawer implements DrawableButton {

    private final RestartButton button;
    private Vector2 p;
    private final float dimensionX;
    private final float dimensionY;

    // STATIC TEXTURE (byt filnamn när du har rätt restart-bild)
    private static final Texture TEXTURE = new Texture(Assets.BUTTON_RESTARTBUTTON);

    public RestartButtonDrawer(RestartButton button) {
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
