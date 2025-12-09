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
        resourceFont = new BitmapFont(Gdx.files.internal("fonts/VT323.fnt"));

        float desiredWorldHeight = .5f;
        float glyphPixelHeight = 32f;

        float scale = desiredWorldHeight / glyphPixelHeight;

        resourceFont.getData().setScale(scale);
        resourceFont.setUseIntegerPositions(false);
    }
}
