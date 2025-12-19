package com.IONA.TowerDefense.view.ui.buttons;

import com.IONA.TowerDefense.model.ui.buttonui.SellButton;
import com.IONA.TowerDefense.view.Assets;
import com.IONA.TowerDefense.view.ui.Fonts;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

public final class SellButtonDrawer implements DrawableButton {

    private final SellButton button;
    private Vector2 p;
    private final float dimensionX;
    private final float dimensionY;
    private BitmapFont font;

    // STATIC TEXTURE — byt filnamnet senare när du har riktig sell-knapp
    private static final Texture TEXTURE = new Texture(Assets.BUTTON_SELLBUTTON);

    public SellButtonDrawer(SellButton button) {
        this.button = button;
        this.dimensionX = button.getWidth();
        this.dimensionY = button.getHeight();
        this.font = Fonts.GOTHIC_FONT_BOLD_5;
    }

    @Override
    public void draw(SpriteBatch batch, ShapeRenderer shapeRenderer, float delta) {
        p = button.getButtonPosition();
        batch.draw(TEXTURE, p.x, p.y, dimensionX, dimensionY);
        font.setColor(0.859f, 0.824f, 0.773f, 1f);
        font.draw(batch, ("SELL: 56 bytes"), p.x+0.08f, p.y+dimensionY-0.001f);

    }

    public static void disposeStatic() {
        if (TEXTURE != null) TEXTURE.dispose();
    }
}
