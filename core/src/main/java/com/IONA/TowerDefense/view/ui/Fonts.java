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

    public static BitmapFont GOTHIC_FONT_DEFAULT_1;
    public static BitmapFont GOTHIC_FONT_DEFAULT_2;
    public static BitmapFont GOTHIC_FONT_DEFAULT_3;
    public static BitmapFont GOTHIC_FONT_DEFAULT_4;
    public static BitmapFont GOTHIC_FONT_DEFAULT_5;
    public static BitmapFont GOTHIC_FONT_DEFAULT_6;
    public static BitmapFont GOTHIC_FONT_DEFAULT_7;
    public static BitmapFont GOTHIC_FONT_DEFAULT_8;
    public static BitmapFont GOTHIC_FONT_DEFAULT_9;
    public static BitmapFont GOTHIC_FONT_DEFAULT_10;
    public static BitmapFont GOTHIC_FONT_DEFAULT_11;
    public static BitmapFont GOTHIC_FONT_DEFAULT_12;


    public static BitmapFont GOTHIC_FONT_BOLD_1;
    public static BitmapFont GOTHIC_FONT_BOLD_2;
    public static BitmapFont GOTHIC_FONT_BOLD_3;
    public static BitmapFont GOTHIC_FONT_BOLD_4;
    public static BitmapFont GOTHIC_FONT_BOLD_5;
    public static BitmapFont GOTHIC_FONT_BOLD_6;
    public static BitmapFont GOTHIC_FONT_BOLD_7;
    public static BitmapFont GOTHIC_FONT_BOLD_8;
    public static BitmapFont GOTHIC_FONT_BOLD_9;
    public static BitmapFont GOTHIC_FONT_BOLD_10;
    public static BitmapFont GOTHIC_FONT_BOLD_11;
    public static BitmapFont GOTHIC_FONT_BOLD_12;

    public static void load() {
        GOTHIC_FONT_DEFAULT_1 = createFont(0.0005f);
        GOTHIC_FONT_DEFAULT_2 = createFont(0.0006f);
        GOTHIC_FONT_DEFAULT_3 = createFont(0.0007f);
        GOTHIC_FONT_DEFAULT_4 = createFont(0.0008f);
        GOTHIC_FONT_DEFAULT_5 = createFont(0.0009f);
        GOTHIC_FONT_DEFAULT_6 = createFont(0.0010f);
        GOTHIC_FONT_DEFAULT_7 = createFont(0.0011f);
        GOTHIC_FONT_DEFAULT_8 = createFont(0.0012f);
        GOTHIC_FONT_DEFAULT_9 = createFont(0.0013f);
        GOTHIC_FONT_DEFAULT_10 = createFont(0.0014f);
        GOTHIC_FONT_DEFAULT_11 = createFont(0.0015f);
        GOTHIC_FONT_DEFAULT_12 = createFont(0.0016f);

        GOTHIC_FONT_BOLD_1 = createBoldFont(0.0005f);
        GOTHIC_FONT_BOLD_2 = createBoldFont(0.0006f);
        GOTHIC_FONT_BOLD_3 = createBoldFont(0.0007f);
        GOTHIC_FONT_BOLD_4 = createBoldFont(0.0008f);
        GOTHIC_FONT_BOLD_5 = createBoldFont(0.0009f);
        GOTHIC_FONT_BOLD_6 = createBoldFont(0.0010f);
        GOTHIC_FONT_BOLD_7 = createBoldFont(0.0011f);
        GOTHIC_FONT_BOLD_8 = createBoldFont(0.0012f);
        GOTHIC_FONT_BOLD_9 = createBoldFont(0.0013f);
        GOTHIC_FONT_BOLD_10 = createBoldFont(0.0014f);
        GOTHIC_FONT_BOLD_11 = createBoldFont(0.0015f);
        GOTHIC_FONT_BOLD_12 = createBoldFont(0.0016f);


    }

    private static BitmapFont createFont(float scale) {
        BitmapFont f = new BitmapFont(Gdx.files.internal(Assets.FONT_GOTHIC_FNT));
        f.setUseIntegerPositions(false);
        f.getData().setScale(scale);
        f.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        return f;
    }

    private static BitmapFont createBoldFont(float scale) {
        BitmapFont f = new BitmapFont(Gdx.files.internal(Assets.FONT_GOTHIC_BOLD_FNT));
        f.setUseIntegerPositions(false);
        f.getData().setScale(scale);
        f.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        return f;
    }

    public static void dispose() {
        GOTHIC_FONT_DEFAULT_1.dispose();
        GOTHIC_FONT_DEFAULT_2.dispose();
        GOTHIC_FONT_DEFAULT_3.dispose();
        GOTHIC_FONT_DEFAULT_4.dispose();
        GOTHIC_FONT_DEFAULT_5.dispose();
        GOTHIC_FONT_DEFAULT_6.dispose();
        GOTHIC_FONT_DEFAULT_7.dispose();
        GOTHIC_FONT_DEFAULT_8.dispose();
        GOTHIC_FONT_DEFAULT_9.dispose();
        GOTHIC_FONT_DEFAULT_10.dispose();
        GOTHIC_FONT_DEFAULT_11.dispose();
        GOTHIC_FONT_DEFAULT_12.dispose();

        GOTHIC_FONT_BOLD_1.dispose();
        GOTHIC_FONT_BOLD_2.dispose();
        GOTHIC_FONT_BOLD_3.dispose();
        GOTHIC_FONT_BOLD_4.dispose();
        GOTHIC_FONT_BOLD_5.dispose();
        GOTHIC_FONT_BOLD_6.dispose();
        GOTHIC_FONT_BOLD_7.dispose();
        GOTHIC_FONT_BOLD_8.dispose();
        GOTHIC_FONT_BOLD_9.dispose();
        GOTHIC_FONT_BOLD_10.dispose();
        GOTHIC_FONT_BOLD_11.dispose();
        GOTHIC_FONT_BOLD_12.dispose();
    }

}
