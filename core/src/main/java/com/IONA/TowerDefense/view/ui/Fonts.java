package com.IONA.TowerDefense.view.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.PixmapPacker;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

public class Fonts {
    public static BitmapFont resourceFont;

    public static void load(){
        FreeTypeFontGenerator generator =
            new FreeTypeFontGenerator(Gdx.files.internal("fonts/10Pixel-Bold.otf"));

        FreeTypeFontGenerator.FreeTypeFontParameter parameter =
            new FreeTypeFontGenerator.FreeTypeFontParameter();

        parameter.size = 20;
        parameter.color = Color.WHITE;
        parameter.magFilter = Texture.TextureFilter.Linear;
        parameter.minFilter = Texture.TextureFilter.Linear;

        parameter.packer = new PixmapPacker(
            512, 512,
            Pixmap.Format.RGBA8888,
            2,
            false
        );

        resourceFont = generator.generateFont(parameter);
        //generator.dispose();

        resourceFont.getData().setScale(.075f);
/*        resourceFont.getData().setLineHeight(resourceFont.getLineHeight() * 1.1f);
        resourceFont.setUseIntegerPositions(true);
        resourceFont.getRegion().getTexture().setFilter(
            Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);*/
    }
}
