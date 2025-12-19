package com.IONA.TowerDefense.view.ui.buttons;

import com.IONA.TowerDefense.model.ui.towerui.sideMenu.UpgradeMenuItem;
import com.IONA.TowerDefense.view.Assets;
import com.IONA.TowerDefense.view.ui.Fonts;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

public final class DamageUpgradeDrawer implements DrawableButton {

    private Vector2 p;
    private final UpgradeMenuItem item;
    private final float dimensionX;
    private final float dimensionY;
    private BitmapFont font;

    // STATIC TEXTURE shared by all instances
    private static final Texture TEXTURE = new Texture(Assets.BUTTON_UPGRADE_BASEUPGRADE);

    public DamageUpgradeDrawer(UpgradeMenuItem item) {
        this.item = item;
        this.dimensionX = item.getWidth();
        this.dimensionY = item.getHeight();
        this.font = Fonts.GOTHIC_FONT_BOLD_6;
    }

    @Override
    public void draw(SpriteBatch batch, ShapeRenderer shapeRenderer, float delta) {
        p = item.getButtonPosition();
        batch.draw(TEXTURE, p.x, p.y, dimensionX, dimensionY);
        font.setColor(0.145f, 0.153f, 0.141f, 1f);
        font.draw(batch, ("+ DAMAGE: " + item.getNextUpgrade().getCost() + " byte"), p.x + 0.08f, p.y + dimensionY + 0.01f);
    }

    public static void disposeStatic() {
        if (TEXTURE != null) TEXTURE.dispose();
    }
}
