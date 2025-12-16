package com.IONA.TowerDefense.view.ui.player;

import com.IONA.TowerDefense.model.ui.playerui.ResourceHP;
import com.IONA.TowerDefense.model.ui.playerui.ResourceMoney;
import com.IONA.TowerDefense.view.Assets;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

public final class HpDrawer implements DrawableResource{

    private static final Texture TEXTURE = new Texture(Assets.FONT_GOTHIC_PNG);
    private static final BitmapFont font = new BitmapFont(Gdx.files.internal(Assets.FONT_GOTHIC_FNT),new TextureRegion(TEXTURE));

    Vector2 p;
    ResourceHP hp;

    static {
        TEXTURE.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);
        font.getData().setScale(3f);
    }

    public HpDrawer(ResourceHP hp){
        this.hp = hp;
        this.p = hp.getPosition();
    }

    @Override
    public void draw(SpriteBatch batch, ShapeRenderer shapeRenderer, float delta){
        String text = hp.getTextBar();
        font.draw(batch, text, p.x, p.y);
        font.draw(batch, "ABC", 50, 50);

    }
}
