package com.IONA.TowerDefense.view.ui.player;

import com.IONA.TowerDefense.model.ui.playerui.ResourceHP;
import com.IONA.TowerDefense.model.ui.playerui.ResourceMoney;
import com.IONA.TowerDefense.view.Assets;
import com.IONA.TowerDefense.view.ui.Fonts;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

public final class HpDrawer implements DrawableResource{

    private final ResourceHP hp;
    private final Vector2 p;
    private BitmapFont font;

    public HpDrawer(ResourceHP hp) {
        this.hp = hp;
        this.p = hp.getPosition();
        this.font = Fonts.GOTHIC_FONT_BOLD_8;
    }

    @Override
    public void draw(SpriteBatch batch, ShapeRenderer shapeRenderer, float delta) {
        font.setColor(0.859f, 0.824f, 0.773f, 1f);
        font.draw(batch, (hp.getTextBar() + "/100 HP"), p.x, p.y);
    }
}
