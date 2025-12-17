package com.IONA.TowerDefense.view.ui;

import com.IONA.TowerDefense.view.Assets;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.PixmapPacker;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

public class Fonts {

    public static BitmapFont GOTHIC_FONT;

    public static void load() {
        Texture texture = new Texture(Gdx.files.internal(Assets.FONT_GOTHIC_PNG));
        texture.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);

        GOTHIC_FONT = new BitmapFont(
            Gdx.files.internal(Assets.FONT_GOTHIC_FNT)
        );

        GOTHIC_FONT.setUseIntegerPositions(false);
        GOTHIC_FONT.getData().setScale(0.002f); //
    }

    public static void dispose() {
        GOTHIC_FONT.dispose();
    }
}
