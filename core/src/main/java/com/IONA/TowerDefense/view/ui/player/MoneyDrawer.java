package com.IONA.TowerDefense.view.ui.player;

import com.IONA.TowerDefense.model.ui.playerui.ResourceMoney;
import com.IONA.TowerDefense.view.Assets;
import com.IONA.TowerDefense.view.ui.Fonts;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

import static java.awt.Color.white;

public final class MoneyDrawer implements DrawableResource {

    private final ResourceMoney money;
    private final Vector2 p;
    private BitmapFont font;

    public MoneyDrawer(ResourceMoney money) {
        this.money = money;
        this.p = money.getPosition();
        this.font = Fonts.GOTHIC_FONT_BOLD_8;
    }

    @Override
    public void draw(SpriteBatch batch, ShapeRenderer shapeRenderer, float delta) {
        batch.enableBlending();
        //batch.setColor(1f, 1f, 1f, 1f);   // ← VIKTIGT
        font.setColor(0.859f, 0.824f, 0.773f, 1);
        font.draw(batch, (money.getTextBar() + " bytes"), p.x, p.y);

    }
}
